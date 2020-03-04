package com.ascending.repository;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.Location;
import com.ascending.repository.LocationDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class LocationDaoTest {
    private LocationDaoImpl locationDaoImpl = new LocationDaoImpl();
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    private Location location;

    @Before
    public void setup() {
        location = new Location();
        location.setAddress("falls church");
        location.setEmail("1093599417@qq.com");
        location.setName("Feixiong");
        location.setPhone_number("202-718-7348");
        location.setSeller_id(1L);
        locationDaoImpl.save(location);
        //assert(0 != locations.getId());
    }

    @After
    public void tearDown() {
        if (location != null)
            Assert.assertTrue(locationDaoImpl.delete(location.getName()));
    }

    @Test
    //@Transactional
    public void getLocationTest() {
        List<Location> locations = locationDaoImpl.getLocations();
        int expectedNumOfloca = 5;
        Assert.assertEquals(expectedNumOfloca, locations.size());
    }

    @Test
    public void getLocationByIdTest() {
        String testName = "location1";
        Location location = locationDaoImpl.getLocationById(1L);
        Assert.assertTrue(location.getName().equals("location1"));
    }

    @Test
    public void updateTest() {

        location.setName("123");
        locationDaoImpl.save(location);
        Assert.assertEquals("123", location.getName());
    }

    @Test
    public void save() {
        //Assert.assertNotEquals(Long.valueOf(0) , locations.getId());
        assert (0 != location.getId());

    }


//    public void getLocationAndProductsByTest(){
//        List<Location> locations = locationDaoImpl.getLocations();
//
//    }
}
