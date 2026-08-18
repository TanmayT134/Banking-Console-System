package com.tanmay.corebanking.test;

import com.tanmay.corebanking.enums.*;
import com.tanmay.corebanking.model.*;
import com.tanmay.corebanking.repository.*;
import com.tanmay.corebanking.service.*;
import java.math.*;

public class CoreBankingSmokeTest {
    public static void main(String[] x) {
        var u = new UserRepository();
        var a = new AccountRepository();
        var t = new TransactionRepository();
        var auth = new AuthenticationService(u);
        var as = new AccountService(a, u);
        var ts = new TransactionService(a, t);
        var user = auth.register("Test User", "test@example.com", "9999999999", "Pune", "01-01-2000", "1234");
        var acc = as.createAccount(user, AccountType.SAVINGS, new BigDecimal("100000"));
        ts.deposit(acc.getAccountNumber(), new BigDecimal("5000"));
        ts.withdraw(acc.getAccountNumber(), new BigDecimal("1000"));
        if (acc.getBalance().compareTo(new BigDecimal("104000")) != 0)
            throw new AssertionError();
        System.out.println("ALL CORE BANKING SMOKE TESTS PASSED");
    }
}
