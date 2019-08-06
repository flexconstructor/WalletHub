package ru.flexconstructor.WalletHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.flexconstructor.WalletHub.entity.LogItem;

import java.util.Date;
import java.util.List;

/**
 * Log item repository.
 */
@Repository
public interface LogItemRepository extends JpaRepository<LogItem, Long> {

    /**
     * Returns list of clients IP which presents in log file more then threshold times between start
     * and end date.
     *
     * @param threshold   threshold
     * @param start       start date
     * @param end         end date
     * @return            Loist of IPs.
     */
    @Query(value = "SELECT l.ip FROM accesslog l " +
            "WHERE " +
            "l.date BETWEEN :startDate " +
            "AND :endDate " +
            "GROUP BY l.ip " +
            "HAVING COUNT(l.ip) > :threshold")
     List<String> getMoreThenThreshold(
             @Param("threshold") Long threshold,
             @Param("startDate") Date start,
             @Param("endDate") Date end);
}
