package com.ascending.Service;

import com.ascending.model.User;
import com.ascending.repository.UserDao;
import com.ascending.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {

    @Autowired
    private UserService userService;
    private User user = new User();

    @Before
    public void setUp() {
//        user = new User();
        user.setEmail("1093599417@qq.com");
        user.setName("Feixiong Meng");
        user.setFirstname("Feixiong");
        user.setLastname("Meng");
        user.setPassword("930715abcd");
        userService.save(user);
    }

    @After
    public void tearDown() {
        if(user != null){
            userService.deleteUserByName(user.getName());
        }
    }

    @Test
    public void getUserByCredentialsTest(){
        String testName = "Feixiong Meng";
        User testUser = userService.getUserByCredentials("1093599417@qq.com","930715abcd");
        Assert.assertEquals(testUser.getName(),testName);
    }


}
