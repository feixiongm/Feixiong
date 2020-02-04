package com.ascending.jdbc;

import org.junit.Test;

import java.util.List;

public class locationDaoTest {
    private locationDao LocationDao = new locationDao();

    @Test
    public void getlocationDaoTest(){
        System.out.println("Test method 1");
        List<locationDao> Location = locationDao.getLocation();
    }
}
