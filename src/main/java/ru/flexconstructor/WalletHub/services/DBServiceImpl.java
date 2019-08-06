package ru.flexconstructor.WalletHub.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.flexconstructor.WalletHub.entity.LogItem;
import ru.flexconstructor.WalletHub.repository.LogItemRepository;

import java.util.Date;
import java.util.List;

/**
 * {@link DBService} implementation.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DBServiceImpl implements DBService {

    /**
     * Instance of {@link LogItemRepository}.
     */
    private final LogItemRepository logItemRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(LogItem item){

        try {
            this.logItemRepository.save(item);
        } catch (Exception ex){
            log.info("Item exists");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String>execute(Long threshold, Date start, Date end){
        return this.logItemRepository.getMoreThenThreshold(threshold, start, end);
    }
}
