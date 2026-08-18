package com.tanmay.corebanking.repository;

import com.tanmay.corebanking.model.User;
import java.util.*;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public synchronized void save(User u) {
        users.put(u.getUserId(), u);
    }

    public Optional<User> findByEmail(String e) {
        return users.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(e)).findFirst();
    }

    public boolean existsByEmail(String e) {
        return findByEmail(e).isPresent();
    }

    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }
}
