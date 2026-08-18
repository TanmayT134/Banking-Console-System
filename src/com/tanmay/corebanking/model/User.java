package com.tanmay.corebanking.model;

import java.util.*;

public class User {
    private final String userId;
    private String fullName, email, mobileNumber, address, dateOfBirth, pinHash;
    private final List<String> accountNumbers = new ArrayList<>();

    public User(String userId, String fullName, String email, String mobileNumber, String address, String dateOfBirth,
            String pinHash) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.pinHash = pinHash;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPinHash() {
        return pinHash;
    }

    public List<String> getAccountNumbers() {
        return Collections.unmodifiableList(accountNumbers);
    }

    public void changePin(String h) {
        pinHash = h;
    }

    public void addAccountNumber(String n) {
        if (!accountNumbers.contains(n))
            accountNumbers.add(n);
    }

    public boolean hasAccount(String n) {
        return accountNumbers.contains(n);
    }
}
