package com.tanmay.corebanking.model;

import com.tanmay.corebanking.enums.AccountType;
import java.math.*;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(String n, String u, BigDecimal b) {
        super(n, u, AccountType.SAVINGS, b);
    }

    public BigDecimal calculateInterest() {
        return getBalance().multiply(new BigDecimal("0.035")).setScale(2, RoundingMode.HALF_UP);
    }
}
