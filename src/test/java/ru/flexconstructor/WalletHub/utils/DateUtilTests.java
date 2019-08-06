package ru.flexconstructor.WalletHub.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTests {

    @Test
    public void parseDateTest(){
        String dateStr = "2017-01-01 23:59:31.334";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 31);
        calendar.set(Calendar.MILLISECOND, 334);
        Date date = null;
        try {
            date= DateUtil.convertDate(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
        }catch (Exception ex){
            assertNull(ex);
        }
        assertNotNull(date);
        assertEquals(calendar.getTimeInMillis(), date.getTime());
    }
}
