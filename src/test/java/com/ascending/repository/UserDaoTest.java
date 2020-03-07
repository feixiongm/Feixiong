package com.ascending.repository;

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
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setEmail("1093599417@qq.com");
        user.setName("Feixiong Meng");
        user.setFirstname("Feixiong");
        user.setLastname("Meng");
        user.setPassword("930715abcd");
        userDao.save(user);
    }

    @After
    public void tearDown() {
        if(user != null){
            userDao.deleteUserByName(user.getName());
        }
    }

    @Test
    public void getUserByCredentialsTest(){
        String testName = "Feixiong Meng";
        User testUser = userDao.getUserByCredentials("1093599417@qq.com","930715abcd");
        Assert.assertEquals(testUser.getName(),testName);
    }

}
