package ru.flexconstructor.WalletHub.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.flexconstructor.WalletHub.entity.LogItem;
import ru.flexconstructor.WalletHub.repository.LogItemRepository;

import java.util.Date;

import static org.mockito.ArgumentMatchers.eq;

public class DBServiceTest {

    @Mock
    private LogItemRepository logItemRepository;

    @InjectMocks
    private DBServiceImpl dbService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addItemTest(){
        LogItem logItem = Mockito.mock(LogItem.class);
        this.dbService.addItem(logItem);
        Mockito.verify(this.logItemRepository).save(eq(logItem));
    }

    @Test
    public void executeTest(){
        Date startDate = Mockito.mock(Date.class);
        Date endDate = Mockito.mock(Date.class);
        this.dbService.execute(10L, startDate, endDate);
        Mockito.verify(this.logItemRepository).getMoreThenThreshold(eq(10L), eq(startDate), eq(endDate));
    }
}
