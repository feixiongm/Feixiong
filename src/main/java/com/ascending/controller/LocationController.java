package com.ascending.controller;


import com.ascending.model.Location;
import com.ascending.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    /*
    * GET
    * /location
    * //TODO join fetch in dao
    * */
    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Location> getLocations() {
        List<Location> locations = locationService.getLocations();
        return locations;
    }

    /*
    * POST
    * /location
    * */
    @RequestMapping(value = {""}, method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Location createLocation(@RequestBody Location location) {
        logger.debug("Location: " + location.toString());
        Location loca = locationService.save(location);
        if (loca!=null) logger.error("The location was not saved.");
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
        if (updateLoca!=null) logger.error("The location was not updated.");
        return updateLoca;
    }
    /*
    * DELETE
    * /location
    * /locaName
    * */
    @RequestMapping(value = "/{locaName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteLocation(@PathVariable String locaName) {
        logger.debug("Location name: " + locaName);
        String msg = "The location was deleted.";
        boolean isSuccess = locationService.deleteByName(locaName);
        if (!isSuccess) msg = "The location was not deleted.";
        return msg;
    }
    @RequestMapping(value = "/{id}",  method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Location getLocationById(@PathVariable Long id){
        Location locations = locationService.getLocationById(id);
        return locations;
    }
    /*
      //TODO get by location ID
     */

}
