package com.ascending.service;

import com.ascending.model.Role;
import com.ascending.model.User;
import com.ascending.repository.RoleDao;
import com.ascending.repository.UserDao;
import com.ascending.repository.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

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

    public boolean deleteUserByName(String userName) {
        return userDao.deleteUserByName(userName);
    }

    public User setRole(Long userId, Long roleId) {
        User user = userDao.findUserById(userId);
        List<Role> roleList = user.getRoles();
        roleList.add(roleDao.getRoleById(roleId));
        user.setRoles(roleList);
        userDao.save(user);
        return user;
    }

    public User deleteRole(Long userId, Long roleId) {
        User user = userDao.findUserById(userId);
        List<Role> roleList = user.getRoles();
        //[482,483,484]
        Role role = roleDao.getRoleById(roleId);
            //[482,483]
        //[482] | [485]S

        roleList.remove(role);
        user.setRoles(roleList);
        userDao.save(user);
        return user;
    }
    public static void main(String[] args){
        String str = new String("111");
        String st1 = new String("111");
        System.out.println(str == st1);
    }
}
