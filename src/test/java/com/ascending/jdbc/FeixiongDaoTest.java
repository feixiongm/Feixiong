package com.ascending.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FeixiongDaoTest {
    private FeixiongDao feixiongDao = new FeixiongDao();
    @Test
    public void getFeixiongTest(){
        System.out.println("Test method 1");
        List<Fexiong> feixiong = feixiongDao.getFeixiong();
        int expectedNumOfDept = 5;

        Assert.assertEquals(expectedNumOfDept, feixiong.size());
    }
}
