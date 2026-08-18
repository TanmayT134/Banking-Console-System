package com.tanmay.corebanking.util;

import java.util.concurrent.atomic.AtomicLong;

public final class TransactionIdGenerator {
    private static final AtomicLong C = new AtomicLong(100000);

    private TransactionIdGenerator() {
    }

    public static String generate() {
        return "TXN" + C.incrementAndGet();
    }
}
