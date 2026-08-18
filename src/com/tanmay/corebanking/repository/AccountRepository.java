package com.tanmay.corebanking.repository;

import com.tanmay.corebanking.model.BankAccount;
import java.util.*;

public class AccountRepository {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public synchronized void save(BankAccount a) {
        accounts.put(a.getAccountNumber(), a);
    }

    public Optional<BankAccount> findByAccountNumber(String n) {
        return Optional.ofNullable(accounts.get(n));
    }

    public List<BankAccount> findByUserId(String u) {
        return accounts.values().stream().filter(a -> a.getUserId().equals(u)).toList();
    }

    public List<BankAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }
}
