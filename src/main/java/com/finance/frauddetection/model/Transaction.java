package com.finance.frauddetection.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a financial transaction for fraud detection.
 */
public class Transaction {

    private int id;
    private int customerId;
    private String txnCountry;
    private BigDecimal amount;
    private LocalDateTime txnTimestamp;
    private String status;

    // Constructor
    public Transaction(int id, int customerId, String txnCountry, BigDecimal amount,
                       LocalDateTime txnTimestamp, String status) {
        this.id = id;
        this.customerId = customerId;
        this.txnCountry = txnCountry;
        this.amount = amount;
        this.txnTimestamp = txnTimestamp;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTxnCountry() {
        return txnCountry;
    }

    public void setTxnCountry(String txnCountry) {
        this.txnCountry = txnCountry;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-null and non-negative");
        }
        this.amount = amount;
    }

    public LocalDateTime getTxnTimestamp() {
        return txnTimestamp;
    }

    public void setTxnTimestamp(LocalDateTime txnTimestamp) {
        if (txnTimestamp == null) {
            throw new IllegalArgumentException("Transaction timestamp cannot be null");
        }
        this.txnTimestamp = txnTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }
        this.status = status;
    }

    // Utility method to display transaction details
    public String getDetails() {
        return String.format(
                "Transaction[id=%d, customerId=%d, country=%s, amount=%s, timestamp=%s, status=%s]",
                id, customerId, txnCountry, amount, txnTimestamp, status
        );
    }
}
