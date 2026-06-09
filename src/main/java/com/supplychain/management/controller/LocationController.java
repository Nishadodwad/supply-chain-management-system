package com.supplychain.management.controller;

import com.supplychain.management.entity.Location;
import com.supplychain.management.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(
            LocationService locationService
    ) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location addLocation(
            @RequestBody Location location
    ) {
        return locationService.addLocation(location);
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }
}
