package com.tanmay.corebanking.util;

import java.security.SecureRandom;

public final class OTPGenerator {
    private static final SecureRandom R = new SecureRandom();

    private OTPGenerator() {
    }

    public static String generateOTP() {
        return String.format("%06d", R.nextInt(1000000));
    }
}
