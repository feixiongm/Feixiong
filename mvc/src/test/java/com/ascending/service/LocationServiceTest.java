package com.ascending.service;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.Location;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class LocationServiceTest {
    @Autowired
    private LocationService locationService;
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    private Location location;

    @Before
    public void setup(){
        location = new Location();
        location.setAddress("falls church");
        location.setEmail("1093599417@qq.com");
        location.setName("Feixiong");
        location.setPhone_number("202-718-7348");
        locationService.save(location);
        //assert(0 != locations.getId());
    }

    @After
    public void tearDown(){
        if(location != null)
            locationService.deleteByName(location.getName());
    }

    @Test
    //@Transactional
    public void getLocationTest(){
        Set<Location> locations = locationService.getLocations();
        int expectedNumOfloca = 5;
        Assert.assertEquals(expectedNumOfloca, locations.size());
    }

    @Test
    public void updateTest(){
        location.setName("123");
        locationService.update(location);
        Assert.assertEquals("123", location.getName());
    }

    @Test
    public void save(){
        //Assert.assertNotEquals(Long.valueOf(0) , locations.getId());
        assert(0 != locationService.save(location).getId());

    }
    @Test
    public void deleteTest(){

        boolean isSuccess = locationService.deleteByName(location.getName());
        Assert.assertTrue(isSuccess);

    }

    @Test
    public void getLocationByIdTest(){

        String testName = "hello";
        Location location = locationService.getLocationById(1L);

        Assert.assertEquals(location.getName(),testName);
    }


//    public void getLocationAndProductsByTest(){
//        List<Location> locations = locationDaoImpl.getLocations();
//
//    }
}
