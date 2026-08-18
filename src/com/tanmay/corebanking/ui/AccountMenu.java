package com.tanmay.corebanking.ui;

import com.tanmay.corebanking.enums.AccountType;
import com.tanmay.corebanking.model.*;
import com.tanmay.corebanking.service.AccountService;
import com.tanmay.corebanking.util.InputUtil;

public class AccountMenu {
    private final AccountService s;

    public AccountMenu(AccountService s) {
        this.s = s;
    }

    public void showAccounts(User u) {
        System.out.println(
                "\n============================================================\n  MY ACCOUNTS\n============================================================");
        var xs = s.getUserAccounts(u.getUserId());
        if (xs.isEmpty())
            System.out.println("No accounts found.");
        else
            for (var a : xs)
                System.out.println(a.getAccountNumber() + " | " + a.getAccountType() + " | ₹" + a.getBalance());
        InputUtil.pause();
    }

    public void createNewAccount(User u) {
        System.out.println(
                "\n============================================================\n  CREATE NEW ACCOUNT\n============================================================");
        System.out.println("1. Savings Account\n2. Current Account");
        int c = InputUtil.readIntInRange("Select account type: ", 1, 2);
        var a = s.createAccount(u, c == 1 ? AccountType.SAVINGS : AccountType.CURRENT,
                InputUtil.readPositiveAmount("Initial Deposit: ₹"));
        System.out.println("Account created successfully!\nAccount Number : " + a.getAccountNumber());
        InputUtil.pause();
    }
}
