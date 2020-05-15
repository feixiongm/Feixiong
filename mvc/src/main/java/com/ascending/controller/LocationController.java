package com.ascending.controller;


import com.ascending.jsonView.View;
import com.ascending.model.Location;
import com.ascending.service.LocationService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * GET/TEST
 *
 * @return
 */
@RestController
@RequestMapping(value = {"/locations"})
public class LocationController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LocationService locationService;

    @JsonView(View.Location.class)
    @Cacheable(value = "locations")
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<Location> getLocations() {
        Set<Location> locations = locationService.getLocations();
        return locations;
    }

    /*
     * POST
     * /location
     * */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Location createLocation(@RequestBody Location location) {
        logger.debug("Location: " + location.toString());
        Location loca = locationService.save(location);
        if (loca != null) logger.error("The location was not saved.");
        return loca;
    }

    /*
     * PUT
     * /location
     * */
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Location updateLocation(@RequestBody Location location) {
        logger.debug("Location: " + location.toString());
        Location updateLoca = locationService.save(location);
        if (updateLoca != null) logger.error("The location was not updated.");
        return updateLoca;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Location updateLocationByName(@PathVariable("id") Long id, @RequestParam("name") String name) {
        Location location = locationService.getLocationById(id);
        location.setName(name);
        locationService.update(location);
        return location;
    }

    /*
     * DELETE
     * /location
     * /locaName
     * */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String deleteLocation(@RequestParam("locationName") String locaName) {
        logger.debug("Location name: " + locaName);
        String msg = "The location was deleted.";
        boolean isSuccess = locationService.deleteByName(locaName);
        if (!isSuccess) msg = "The location was not deleted.";
        return msg;
    }
    @JsonView(View.Location.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Cacheable(value = "locations")
    public Location getLocationById(@PathVariable Long id) {
        logger.info("Get location with id {}.", id);
        Location locations = locationService.getLocationById(id);
        return locations;
    }

}
