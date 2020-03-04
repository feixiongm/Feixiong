package com.ascending.repository;

import com.ascending.model.User;

public interface UserDao {
    User save(User user);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
}
