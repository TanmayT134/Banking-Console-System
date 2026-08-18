package com.tanmay.corebanking.util;

import java.nio.charset.StandardCharsets;
import java.security.*;

public final class PinUtil {
    private PinUtil() {
    }

    public static String hashPin(String p) {
        try {
            byte[] h = MessageDigest.getInstance("SHA-256").digest(p.getBytes(StandardCharsets.UTF_8));
            StringBuilder s = new StringBuilder();
            for (byte b : h)
                s.append(String.format("%02x", b));
            return s.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static boolean verifyPin(String p, String h) {
        return MessageDigest.isEqual(hashPin(p).getBytes(StandardCharsets.UTF_8), h.getBytes(StandardCharsets.UTF_8));
    }
}
