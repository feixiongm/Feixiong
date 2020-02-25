package com.ascending.service;

import com.ascending.model.Location;
import com.ascending.repository.LocationDao;
import com.ascending.repository.LocationDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationService {
    private LocationDao locationDao = new LocationDaoImpl();

    public Location save (Location location){
        return locationDao.save(location);
    }
    public boolean delete(String locaName){
        return locationDao.delete(locaName);
    }
    public List<Location> getLocations(){
        return locationDao.getLocations();
    }
    public LocationDao getLocationDao() {
        return locationDao;
    }
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
    public List<Location> getLocationAndProducts(String name){
        return locationDao.getLocationAndProducts(name);
    }
}
