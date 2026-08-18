package com.tanmay.corebanking.ui;

import com.tanmay.corebanking.exception.AuthenticationException;
import com.tanmay.corebanking.model.User;
import com.tanmay.corebanking.service.*;
import com.tanmay.corebanking.enums.AccountType;
import com.tanmay.corebanking.util.*;
import java.math.*;

public class AuthenticationMenu {
    private final AuthenticationService auth;
    private final AccountService accounts;

    public AuthenticationMenu(AuthenticationService a, AccountService s) {
        auth = a;
        accounts = s;
    }

    public User login() {
        System.out.println(
                "\n============================================================\n  CUSTOMER LOGIN\n============================================================");
        try {
            return auth.login(InputUtil.readString("Email: "), readPin("PIN: "));
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User register() {
        System.out.println(
                "\n============================================================\n  NEW CUSTOMER REGISTRATION\n============================================================");
        try {
            String n = InputUtil.readString("Full Name: "), e = InputUtil.readString("Email: "),
                    m = InputUtil.readString("Mobile Number: "), a = InputUtil.readString("Address: "),
                    d = InputUtil.readString("Date of Birth (DD-MM-YYYY): "), p = readPin("Create 4-digit PIN: ");
            while (!p.equals(readPin("Confirm PIN: "))) {
                System.out.println("PINs do not match.");
                p = readPin("Create 4-digit PIN: ");
            }
            User u = auth.register(n, e, m, a, d, p);
            System.out.println("\nCustomer registration successful!\nCustomer ID: " + u.getUserId());
            System.out.println("\n1. Savings Account\n2. Current Account");
            int c = InputUtil.readIntInRange("Select account type: ", 1, 2);
            BigDecimal dep = InputUtil.readPositiveAmount("Initial Deposit: ₹");
            var acc = accounts.createAccount(u, c == 1 ? AccountType.SAVINGS : AccountType.CURRENT, dep);
            System.out.println("\nAccount created successfully!\nAccount Number : " + acc.getAccountNumber()
                    + "\nAccount Type   : " + acc.getAccountType() + "\nInitial Balance: ₹" + acc.getBalance());
            return u;
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String readPin(String m) {
        while (true) {
            String p = InputUtil.readString(m);
            if (PinValidator.isValid(p))
                return p;
            System.out.println("PIN must contain exactly 4 digits.");
        }
    }
}
