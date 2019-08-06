package ru.flexconstructor.WalletHub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Log line entity.
 */
@Entity(name = "accesslog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"hash"})})
public class LogItem {

    /**
     * Entity ID.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Log line date.
     */
    private Date date;

    /**
     * Client remote IP.
     */
    private String ip;

    /**
     * HTTP status.
     */
    private int status;

    /**
     * User Agent data.
     */
    private String usesAgent;

    /**
     * HTTP request type.
     */
    private String request;

    /**
     * Log line hash.
     */
    private int hash;
}
