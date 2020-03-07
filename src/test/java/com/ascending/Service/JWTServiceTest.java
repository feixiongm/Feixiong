package com.ascending.Service;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.User;
import com.ascending.service.JWTService;
import com.ascending.service.UserService;
import io.jsonwebtoken.Claims;
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
public class JWTServiceTest {
    @Autowired
    private UserService userService;
    private User user;
    private JWTService jwtService = new JWTService();
    @Before
    public void setUp() {
        user = new User();
        user.setEmail("1093599417@qq.com");
        user.setName("Feixiong Meng");
        user.setFirstname("Feixiong");
        user.setLastname("Meng");
        user.setPassword("930715abcd");
        userService.save(user);
    }

    @After
    public void tearDown(){
        if(user != null)
            userService.deleteUserByName(user.getName());
    }

    @Test
    public void generateTokenTest(){
        String testToken = jwtService.generateToken(user);
        Assert.assertEquals(testToken.split("\\.").length, 3);
    }

    @Test
    public void decryptJwtTokenTest(){
        String testToken = jwtService.generateToken(user);
        Claims claims = jwtService.decryptJwtToken(testToken);
        Assert.assertEquals(claims.getId(),user.getId().toString());
    }
}
