package ru.flexconstructor.WalletHub.shell;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import reactor.core.publisher.Flux;
import ru.flexconstructor.WalletHub.entity.LogItem;
import ru.flexconstructor.WalletHub.services.DBService;
import ru.flexconstructor.WalletHub.services.ParseFileService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class ParseCommandsTest {

    @Mock
    private ParseFileService parseFileService;

    @Mock
    private DBService dbService;

    @InjectMocks
    private ParseCommands parseCommands;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseTest(){
        List<String> lines =new ArrayList<>();
                lines.add("2017-01-01 23:59:19.362|192.168.84.90|\"GET / HTTP/1.1\"|200|\"WalletHubDev/1.0.5 (iPhone; iOS 10.3.3; Scale/2.00)\"");
                lines.add("2017-01-01 23:59:20.047|192.168.62.176|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5 Build/MRA58N) AppleWebKit/537.36(KHTML, like Gecko) Chrome/59.0.3033.0 Mobile Safari/537.36\"");
                lines.add("2017-01-01 23:59:23.451|192.168.146.174|\"GET / HTTP/1.1\"|200|\"Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0\"");
        Flux<String> linesStream = Flux.fromStream(lines.stream());

        try {
            Mockito.when(parseFileService.parseStream(eq("test_file_path"))).thenReturn(linesStream);
        } catch (Exception ex){
            assertNull(ex);
        }

        Mockito.when(this.dbService.execute(eq(10L), any(Date.class),any(Date.class))).thenReturn(Collections.singletonList("complete"));

        this.parseCommands.parse("test_file_path", "2017-01-01 23:59:19", "hourly", "10");
        try {
            Mockito.verify(this.parseFileService).parseStream(eq("test_file_path"));
        }catch (Exception ex){
            assertNull(ex);
        }

        Mockito.verify(this.dbService, Mockito.times(3)).addItem(any(LogItem.class));
        Mockito.verify(this.dbService).execute(eq(10L), any(Date.class), any(Date.class));
    }
}
