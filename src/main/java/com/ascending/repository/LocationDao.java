package com.ascending.repository;

import com.ascending.model.Location;

import java.util.List;
import java.util.Set;

public interface LocationDao {
    Set<Location> getLocations();
    Location save(Location location);
    Boolean deleteByName(String locaName);
    List<Location> getLocationAndProducts(String name);
    Location getLocationById(Long LocationId);
}
