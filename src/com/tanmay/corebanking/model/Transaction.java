package com.tanmay.corebanking.model;

import com.tanmay.corebanking.enums.*;
import java.math.*;
import java.time.*;

public class Transaction {
    private final String transactionId, accountNumber, description, relatedAccountNumber;
    private final TransactionType transactionType;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final TransactionStatus status;

    public Transaction(String id, String acc, TransactionType type, BigDecimal amt, TransactionStatus st, String desc,
            String related) {
        transactionId = id;
        accountNumber = acc;
        transactionType = type;
        amount = amt;
        status = st;
        description = desc;
        relatedAccountNumber = related;
        timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getRelatedAccountNumber() {
        return relatedAccountNumber;
    }
}
