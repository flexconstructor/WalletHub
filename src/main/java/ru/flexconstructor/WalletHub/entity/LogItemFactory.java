package ru.flexconstructor.WalletHub.entity;

import ru.flexconstructor.WalletHub.utils.DateUtil;

import java.util.Date;

/**
 * Factory for create {@link LogItem} instance from given string line.
 */
public class LogItemFactory {

    /**
     * Returns new instance of {@link LogItem}.
     *
     * @param line Log line.
     * @return {@link LogItem} new instance.
     * @throws Exception some exception.
     */
    public static LogItem parseLine(String line) throws Exception {
        String[] items = line.split("\\|");

        Date itemDate;
        try {
            itemDate = DateUtil.convertDate(items[0].trim(), "yyyy-MM-dd HH:mm:ss.SSS");
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return LogItem.builder()
                .date(itemDate)
                .ip(items[1])
                .request(removeQuotes(items[2]))
                .status(Integer.parseInt(items[3]))
                .usesAgent(removeQuotes(items[4]))
                .hash(line.hashCode())
                .build();
    }

    private static String removeQuotes(String string) {
        return string.replace("\"", "");
    }
}
