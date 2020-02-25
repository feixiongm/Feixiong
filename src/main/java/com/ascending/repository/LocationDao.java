package com.ascending.repository;

import com.ascending.model.Location;

import java.util.List;

public interface LocationDao {
    List<Location> getLocations();
    Location save(Location location);
    Boolean delete(String locaName);
    List<Location> getLocationAndProducts(String name);
    public Location getLocationById(int id);
}
