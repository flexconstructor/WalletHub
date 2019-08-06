package ru.flexconstructor.WalletHub.utils;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static Date convertDate(String dateStr, String pattern)throws Exception{
        return new SimpleDateFormat(pattern, Locale.ENGLISH).parse(dateStr);
    }

    public static Date addDuration(Date startDate, String duration) throws Exception{
        switch (duration){
            case "hourly":
                return   Date.from(startDate.toInstant().plus(1, ChronoUnit.HOURS));
            case "daily":
                return Date.from(startDate.toInstant().plus(1, ChronoUnit.DAYS));
            default:
                throw new Exception(String.format("Duration %s not supported", duration));
        }
    }
}
