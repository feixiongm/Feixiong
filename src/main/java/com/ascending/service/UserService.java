package com.ascending.service;

import com.ascending.model.User;
import com.ascending.repository.UserDao;
import com.ascending.repository.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User save(User user) {
        return userDao.save(user);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User getUserByCredentials(String email, String password) {
        return userDao.getUserByCredentials(email, password);
    }

    public User getUserById(Long id) {
        return userDao.findUserById(id);
    }

    public boolean deleteUserByName(String userName){
        return userDao.deleteUserByName(userName);
    }

}
