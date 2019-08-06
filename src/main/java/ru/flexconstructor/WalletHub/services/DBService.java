package ru.flexconstructor.WalletHub.services;

import ru.flexconstructor.WalletHub.entity.LogItem;

import java.util.Date;
import java.util.List;

/**
 * Data base service.
 */
public interface DBService {
    /**
     * Adds new {@link LogItem} entity instance into Data Base.
     *
     * @param item   {@link LogItem} instance.
     */
    void addItem(LogItem item);

    /**
     * Execute select query which returns list of clients IPs which presents into log file more then
     * threshold times between start and end dates.
     *
     * @param threshold  threshold.
     * @param start      start date.
     * @param end        end date.
     * @return           list of IPs.
     */
    List<String>execute(Long threshold, Date start, Date end);
}
