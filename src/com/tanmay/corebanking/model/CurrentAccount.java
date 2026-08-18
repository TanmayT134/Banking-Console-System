package com.tanmay.corebanking.model;

import com.tanmay.corebanking.enums.AccountType;
import java.math.*;

public class CurrentAccount extends BankAccount {
    public CurrentAccount(String n, String u, BigDecimal b) {
        super(n, u, AccountType.CURRENT, b);
    }

    public BigDecimal calculateInterest() {
        return BigDecimal.ZERO;
    }
}
