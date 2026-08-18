package com.tanmay.corebanking.service;

import com.tanmay.corebanking.model.*;
import com.tanmay.corebanking.util.EmailUtil;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmailMiniStatementService {
    private final AccountService accounts;
    private final TransactionService transactions;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public EmailMiniStatementService(AccountService a, TransactionService t) {
        accounts = a;
        transactions = t;
    }

    public void sendMiniStatement(User u, String accountNumber) {
        BankAccount a = accounts.getAccount(accountNumber);
        if (!a.getUserId().equals(u.getUserId()))
            throw new IllegalStateException("You are not authorized to access this account.");
        StringBuilder b = new StringBuilder();
        b.append("CORE BANKING SYSTEM\nMINI STATEMENT\n\nCustomer Name : ").append(u.getFullName())
                .append("\nCustomer ID   : ").append(u.getUserId()).append("\nAccount No.   : ")
                .append(a.getAccountNumber()).append("\nAccount Type  : ").append(a.getAccountType())
                .append("\nEmail         : ").append(u.getEmail())
                .append("\n\nTransaction History - Last 5 Transactions\n\n");
        List<com.tanmay.corebanking.model.Transaction> ts = transactions.getMiniStatement(accountNumber);
        if (ts.isEmpty())
            b.append("No transactions found.\n");
        else
            for (var t : ts)
                b.append("Transaction ID : ").append(t.getTransactionId()).append("\nDate/Time      : ")
                        .append(t.getTimestamp().format(fmt)).append("\nType           : ")
                        .append(t.getTransactionType()).append("\nAmount         : ₹").append(t.getAmount().setScale(2))
                        .append("\nStatus         : ").append(t.getStatus()).append("\nDescription    : ")
                        .append(t.getDescription())
                        .append("\n------------------------------------------------------------\n");
        b.append("\nCurrent Balance : ₹").append(a.getBalance().setScale(2))
                .append("\n\nThis is a system-generated statement.\nPlease do not reply to this email.\n");
        EmailUtil.sendEmail(u.getEmail(), "Core Banking - Mini Statement - " + a.getAccountNumber(), b.toString());
    }
}
