package ru.flexconstructor.WalletHub.entity;

import org.junit.Test;
import ru.flexconstructor.WalletHub.utils.DateUtil;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LogItemFactoryTest {

    @Test
    public void parseLineTest(){
        String testString = "2017-01-01 23:59:17.778|192.168.136.1|\"GET / HTTP/1.1\"|200|\"test user agent\"";
        LogItem logItem = null;
        try{
            logItem = LogItemFactory.parseLine(testString);
        }catch(Exception ex){
            assertNull(ex);
        }
        assertNotNull(logItem);
        Date checkDate = null;
        try{
            checkDate = DateUtil.convertDate("2017-01-01 23:59:17.778", "yyyy-MM-dd HH:mm:ss.SSS");
        }catch (Exception ex){
            assertNull(ex);
        }
        assertEquals(checkDate, logItem.getDate());
        assertEquals("192.168.136.1", logItem.getIp());
        assertEquals("GET / HTTP/1.1", logItem.getRequest());
        assertEquals(200, logItem.getStatus());
        assertEquals("test user agent", logItem.getUsesAgent());
    }
}
