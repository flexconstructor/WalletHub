package ru.flexconstructor.WalletHub.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.flexconstructor.WalletHub.entity.LogItemFactory;
import ru.flexconstructor.WalletHub.services.DBService;
import ru.flexconstructor.WalletHub.services.ParseFileService;
import ru.flexconstructor.WalletHub.utils.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * Shell command controller.
 */
@ShellComponent
@Slf4j
@RequiredArgsConstructor
public class ParseCommands {

    /**
     * Instance of {@link ParseFileService}
     */
    private final ParseFileService parseFileService;

    /**
     * Instance of {@link DBService}.
     */
    private final DBService dbService;

    /**
     * Parses given access log files and returns list of IPs which presents in log file more then
     * threshold times from start date to given duration.
     *
     * @param file        access log file path.
     * @param startDate   start date
     * @param duration    duration
     * @param threshold   threshold.
     *
     * @return  string.
     */
    @ShellMethod(value = "Parse access log file")
    public String parse(@ShellOption(value = {"-f", "--accesslog"},
            defaultValue = "./access.log",
            help = "Enter access.log file path") String file,
                        @ShellOption(value = {"-s", "--startDate"},
                                defaultValue = "2017-01-01 15:00:00",
                                help = "Enter start date") String startDate,
                       @ShellOption(value = {"-d", "--duration"},
                                defaultValue = "hourly",
                                help = "Enter duration") String duration,
                        @ShellOption(value = {"-t", "--threshold"},
                                defaultValue = "100", help = "Enter threshold") String threshold) {
       final StringBuffer stringBuffer = new StringBuffer("Found: \n");
        Date dateStart;
        try {
            dateStart = DateUtil.convertDate(startDate, "yyyy-MM-dd HH:mm:ss");
        }catch (Exception ex){
            return ex.getMessage();
        }
        Date dateEnd;
       try{
           dateEnd = DateUtil.addDuration(dateStart, duration);
       } catch(Exception ex){
           return ex.getMessage();
       }
        try{
             this.parseFileService.parseStream(file)
                    .map(line ->{
                        try{
                           return LogItemFactory.parseLine(line);
                        }catch (Exception ex){
                            return null;
                        }
                    }).filter(Objects::nonNull)
                    .filter(item -> item.getDate().after(dateStart) && item.getDate().before(dateEnd))
                    .doOnNext(this.dbService::addItem)
                     .doOnComplete(()-> dbService.execute(Long.parseLong(threshold), dateStart, dateEnd)
                             .forEach(ip -> stringBuffer.append(ip).append("\n")))
                     .subscribe();
        }catch (Exception ex){
            return ex.getMessage();
        }
        return stringBuffer.toString();
    }
}
