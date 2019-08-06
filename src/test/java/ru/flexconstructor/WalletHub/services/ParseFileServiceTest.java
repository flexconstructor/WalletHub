package ru.flexconstructor.WalletHub.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParseFileServiceTest {

    private ParseFileService parseFileService;

    @Before
    public void setUp(){
        this.parseFileService = new ParseFileServiceImpl();
    }

    @Test
    public void parseFileTest(){
        try {
            assertEquals(3, this.parseFileService.parseStream("./src/test/resources/test_access.log").toStream().count());
        }catch (Exception ex){
            assertNull(ex);
        }
    }
}
