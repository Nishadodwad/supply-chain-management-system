package com.supplychain.management.controller;

import com.supplychain.management.entity.Driver;
import com.supplychain.management.service.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(
            DriverService driverService
    ) {
        this.driverService = driverService;
    }

    @PostMapping
    public Driver addDriver(
            @RequestBody Driver driver
    ) {
        return driverService.addDriver(driver);
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriver(
            @PathVariable Long id
    ) {
        return driverService.getDriver(id);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(
            @PathVariable Long id,
            @RequestBody Driver driver
    ) {
        return driverService.updateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    public String deleteDriver(
            @PathVariable Long id
    ) {
        driverService.deleteDriver(id);
        return "Driver deleted successfully";
    }
}