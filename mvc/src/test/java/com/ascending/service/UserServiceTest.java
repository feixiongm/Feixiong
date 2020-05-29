package com.ascending.service;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private User user = new User();

    @Before
    public void setUp() {
//        user = new User();
        user.setEmail("1093599417abc@qq.com");
        user.setName("Feixiong Mengabc");
        user.setFirstname("Feixiongabc");
        user.setLastname("Mengabc");
        user.setPassword("930715abcdabc");
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
        String testName = "Feixiong Mengabc";
        String password = user.getPassword();
        User testUser = userService.getUserByCredentials("1093599417abc@qq.com",password);
        Assert.assertEquals(testUser.getName(),testName);
    }
}
