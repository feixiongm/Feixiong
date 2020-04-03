package com.ascending.service;

import com.ascending.model.Location;
import com.ascending.repository.LocationDao;
import com.ascending.repository.LocationDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;


@Service
public class LocationService {
    @Autowired
    private LocationDao locationDao;

    public Location save (Location location){
        return locationDao.save(location);
    }
    public Location update(Location location){
        return locationDao.save(location);
    }
    public boolean deleteByName(String locaName){
        return locationDao.deleteByName(locaName);
    }
    public Set<Location> getLocations(){
        return locationDao.getLocations();
    }
    public Location getLocationById(Long id){return locationDao.getLocationById(id);}
}
