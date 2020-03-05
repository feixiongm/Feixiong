package com.ascending.Service;

import com.ascending.init.ApplicationBootstrap;
import com.ascending.model.Location;
import com.ascending.repository.LocationDaoImpl;
import com.ascending.service.LocationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class LocationServiceTest {
    private LocationService locationService = new LocationService();
    //    private Logger logger = LoggerFactory.getLogger(getClass());
    private Location location;

    @Before
    public void setup(){
        location = new Location();
        location.setAddress("falls church");
        location.setEmail("1093599417@qq.com");
        location.setName("Feixiong");
        location.setPhone_number("202-718-7348");
        location.setSeller_id(1L);
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
        List<Location> locations = locationService.getLocations();
        int expectedNumOfloca = 5;
        Assert.assertEquals(expectedNumOfloca, locations.size());
    }

    @Test
    public void updateTest(){
        location.setName("123");
        locationService.save(location);
        Assert.assertEquals("123", location.getName());
    }

    @Test
    public void save(){
        //Assert.assertNotEquals(Long.valueOf(0) , locations.getId());
        assert(0 != location.getId());

    }
    @Test
    public void deleteTest(String locaName){

    }


//    public void getLocationAndProductsByTest(){
//        List<Location> locations = locationDaoImpl.getLocations();
//
//    }
}
