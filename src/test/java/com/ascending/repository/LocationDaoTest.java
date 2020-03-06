package com.ascending.repository;

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

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class LocationDaoTest {
    @Autowired
    private LocationDao locationDao;
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    private Location location;

    @Before
    public void setup() {
        location = new Location();
        location.setAddress("falls church");
        location.setEmail("1093599417@qq.com");
        location.setName("Feixiong");
        location.setPhone_number("202-718-7348");
        locationDao.save(location);
        //assert(0 != locations.getId());
    }

    @After
    public void tearDown() {
        if (location != null)
            Assert.assertTrue(locationDao.deleteByName(location.getName()));
    }

    @Test
    //@Transactional
    public void getLocationTest() {
        List<Location> locations = locationDao.getLocations();
        int expectedNumOfloca = 8;
        Assert.assertEquals(expectedNumOfloca, locations.size());
    }

    @Test
    public void getLocationByIdTest() {
        String testName = "location1";
        Location location = locationDao.getLocationById(1L);
        Assert.assertEquals("location1", location.getName());
    }

    @Test
    public void updateTest() {

        location.setName("123");
        locationDao.save(location);
        Assert.assertEquals("123", location.getName());
    }

    @Test
    public void save() {
        //Assert.assertNotEquals(Long.valueOf(0) , locations.getId());
        assert (0 != location.getId());

    }

    @Test
    public void deleteTest() {
        boolean isSuccess = locationDao.deleteByName(location.getName());

        Assert.assertEquals(isSuccess, true);
    }
//    public void getLocationAndProductsByTest(){
//        List<Location> locations = locationDaoImpl.getLocations();
//
//    }
}
