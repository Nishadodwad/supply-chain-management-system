package com.supplychain.management.service;

import com.supplychain.management.entity.Location;
import com.supplychain.management.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(
            LocationRepository locationRepository
    ) {
        this.locationRepository = locationRepository;
    }

    public Location addLocation(
            Location location
    ) {
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}