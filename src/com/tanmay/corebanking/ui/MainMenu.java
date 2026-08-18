package com.tanmay.corebanking.ui;

import com.tanmay.corebanking.model.User;
import com.tanmay.corebanking.service.*;
import com.tanmay.corebanking.util.InputUtil;

public class MainMenu {
    private final AuthenticationService auth;
    private final AuthenticationMenu authenticationMenu;
    private final CustomerMenu customerMenu;

    public MainMenu(AuthenticationService a, AccountService s, TransactionService t, PinChangeService p,
            EmailMiniStatementService e) {
        auth = a;
        authenticationMenu = new AuthenticationMenu(a, s);
        customerMenu = new CustomerMenu(a, s, t, p, e);
    }

    public void start() {
        boolean run = true;
        while (run) {
            System.out.println(
                    "\n╔════════════════════════════════════════════════════════════╗\n║                                                            ║\n║           CORE BANKING CONSOLE APPLICATION                 ║\n║                                                            ║\n╠════════════════════════════════════════════════════════════╣\n║                                                            ║\n║   1. Login                                                 ║\n║   2. New Registration                                      ║\n║   3. Exit                                                  ║\n║                                                            ║\n╚════════════════════════════════════════════════════════════╝\n");
            switch (InputUtil.readIntInRange("Enter your choice: ", 1, 3)) {
                case 1 -> {
                    User u = authenticationMenu.login();
                    if (u != null)
                        customerMenu.show(u);
                }
                case 2 -> {
                    User u = authenticationMenu.register();
                    if (u != null) {
                        System.out.println("\nYou can now login using your registered email and PIN.");
                        InputUtil.pause();
                    }
                }
                case 3 -> {
                    run = false;
                    System.out.println("Thank you for using Core Banking Console Application.\nGoodbye!");
                }
            }
        }
    }
}
