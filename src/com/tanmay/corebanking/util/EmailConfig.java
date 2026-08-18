package com.tanmay.corebanking.util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public final class EmailConfig {
    private static Properties P;

    private EmailConfig() {
    }

    private static synchronized Properties load() {
        if (P != null)
            return P;
        P = new Properties();
        Path[] paths = { Path.of("config", "email.properties"), Path.of("email.properties") };
        for (Path p : paths) {
            if (Files.exists(p))
                try (InputStream in = Files.newInputStream(p)) {
                    P.load(in);
                    break;
                } catch (IOException ignored) {
                }
        }
        return P;
    }

    public static String sender() {
        String v = System.getenv("BANKING_EMAIL");
        if (v == null || v.isBlank())
            v = load().getProperty("BANKING_EMAIL");
        return v;
    }

    public static String password() {
        String v = System.getenv("BANKING_EMAIL_APP_PASSWORD");
        if (v == null || v.isBlank())
            v = load().getProperty("BANKING_EMAIL_APP_PASSWORD");
        return v == null ? null : v.replaceAll("\\s+", "");
    }
}
