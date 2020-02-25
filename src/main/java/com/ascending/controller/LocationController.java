package com.ascending.controller;


import com.ascending.model.Location;
import com.ascending.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * GET/TEST
 *
 * @return
 */
@RestController
@RequestMapping(value = {"/location"})
public class LocationController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private LocationService locationService;
    private Location location;
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Location> getLocations() {

        List<Location> locations = locationService.getLocations();

        return locations;
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Location createLocation(@RequestBody Location location) {
        logger.debug("Location: " + location.toString());
        Location loca = locationService.save(location);
        if (loca!=null) logger.error("The location was not saved.");
        return loca;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Location updateLocation(@RequestBody Location location) {
        logger.debug("Location: " + location.toString());
        Location updateLoca = locationService.save(location);
        if (updateLoca!=null) logger.error("The location was not updated.");
        return updateLoca;
    }

    @RequestMapping(value = "/{locaName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteLocation(@PathVariable String locaName) {
        logger.debug("Location name: " + locaName);
        String msg = "The location was deleted.";
        boolean isSuccess = locationService.delete(locaName);
        if (!isSuccess) msg = "The location was not deleted.";
        return msg;
    }

}
