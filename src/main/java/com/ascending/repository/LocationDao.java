package com.ascending.repository;

import com.ascending.model.Location;

import java.util.List;

public interface LocationDao {
    List<Location> getLocations();
    Location save(Location location);
    Boolean deleteByName(String locaName);
    List<Location> getLocationAndProducts(String name);
    Location getLocationById(Long LocationId);
}
