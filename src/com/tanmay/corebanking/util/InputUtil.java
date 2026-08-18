package com.tanmay.corebanking.util;

import java.math.BigDecimal;
import java.util.Scanner;

public final class InputUtil {
    private static final Scanner S = new Scanner(System.in);

    private InputUtil() {
    }

    public static String readString(String m) {
        while (true) {
            System.out.print(m);
            String x = S.nextLine().trim();
            if (!x.isEmpty())
                return x;
            System.out.println("Input cannot be empty.");
        }
    }

    public static int readInt(String m) {
        while (true) {
            try {
                return Integer.parseInt(readString(m));
            } catch (Exception e) {
                System.out.println("Invalid number.");
            }
        }
    }

    public static int readIntInRange(String m, int a, int b) {
        while (true) {
            int x = readInt(m);
            if (x >= a && x <= b)
                return x;
            System.out.println("Please enter a value between " + a + " and " + b + ".");
        }
    }

    public static BigDecimal readPositiveAmount(String m) {
        while (true) {
            try {
                BigDecimal x = new BigDecimal(readString(m));
                if (x.compareTo(BigDecimal.ZERO) > 0)
                    return x;
            } catch (Exception ignored) {
            }
            System.out.println("Amount must be greater than zero.");
        }
    }

    public static void pause() {
        System.out.println("\nPress ENTER to continue...");
        S.nextLine();
    }

    public static void close() {
        S.close();
    }
}
