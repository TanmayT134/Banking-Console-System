package com.tanmay.corebanking.repository;

import com.tanmay.corebanking.model.Transaction;
import java.util.*;

public class TransactionRepository {
    private final Map<String, List<Transaction>> txns = new HashMap<>();

    public synchronized void save(Transaction t) {
        txns.computeIfAbsent(t.getAccountNumber(), k -> new ArrayList<>()).add(t);
    }

    public List<Transaction> findByAccountNumber(String a) {
        return new ArrayList<>(txns.getOrDefault(a, List.of()));
    }

    public List<Transaction> findRecentTransactions(String a, int limit) {
        return txns.getOrDefault(a, List.of()).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed()).limit(limit).toList();
    }
}
