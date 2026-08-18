package com.tanmay.corebanking.service;

import com.tanmay.corebanking.exception.AuthenticationException;
import com.tanmay.corebanking.model.User;
import com.tanmay.corebanking.repository.UserRepository;
import com.tanmay.corebanking.util.*;

public class AuthenticationService {
    private final UserRepository repo;
    private User loggedIn;

    public AuthenticationService(UserRepository r) {
        repo = r;
    }

    public User register(String n, String e, String m, String a, String d, String p) {
        if (!PinValidator.isValid(p))
            throw new AuthenticationException("PIN must contain exactly 4 digits.");
        if (repo.existsByEmail(e))
            throw new AuthenticationException("An account with this email already exists.");
        User u = new User(UserIdGenerator.generate(), n, e, m, a, d, PinUtil.hashPin(p));
        repo.save(u);
        return u;
    }

    public User login(String e, String p) {
        User u = repo.findByEmail(e).orElseThrow(() -> new AuthenticationException("Invalid email or PIN."));
        if (!PinUtil.verifyPin(p, u.getPinHash()))
            throw new AuthenticationException("Invalid email or PIN.");
        loggedIn = u;
        return u;
    }

    public void verifyCurrentPin(User u, String p) {
        if (!PinUtil.verifyPin(p, u.getPinHash()))
            throw new AuthenticationException("Current PIN is incorrect.");
    }

    public void logout() {
        loggedIn = null;
    }

    public boolean isLoggedIn() {
        return loggedIn != null;
    }

    public User getLoggedInUser() {
        if (loggedIn == null)
            throw new AuthenticationException("No user is currently logged in.");
        return loggedIn;
    }
}
