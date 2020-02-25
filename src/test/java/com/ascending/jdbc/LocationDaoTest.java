//package com.ascending.jdbc;
//
//import com.ascending.model.Location;
//import com.ascending.repository.LocationDao;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//
//public class LocationDaoTest {
//    private LocationDao locationDao;
//    private Location testRecord;
//
//    @Before
//    public void setup(){
//        testRecord = new Location();
//        locationDao = new LocationDao();
//        locationDao.save(testRecord);
//    }
//
//    @After
//    public void tearDown(){
//        locationDao.delete(testRecord.getId());
//    }
//    @Test
//    public void getlocationTest(){
//        System.out.println("Test method 1");
//        List<Location> locations = locationDao.getLocations();
//        int expectedNumOfloca = 1;
////        for(Location a : locations){
////            System.out.println(a.getLocations());
////        }
//        Assert.assertEquals(expectedNumOfloca, locations.size());
//    }
//}
