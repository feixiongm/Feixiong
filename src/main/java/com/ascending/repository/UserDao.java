package com.ascending.repository;

import com.ascending.model.User;

public interface UserDao {
    User save(User user);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
    User findUserById(Long id);
    boolean deleteUserByName(String userName);
}
