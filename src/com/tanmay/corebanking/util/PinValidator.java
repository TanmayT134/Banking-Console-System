package com.tanmay.corebanking.util; public final class PinValidator{private PinValidator(){} public static boolean isValid(String p){return p!=null&&p.matches("\\d{4}");}}
