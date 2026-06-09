package com.supplychain.management.service;

import com.supplychain.management.entity.Driver;
import com.supplychain.management.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(
            DriverRepository driverRepository
    ) {
        this.driverRepository = driverRepository;
    }

    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriver(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Driver not found"));
    }

    public Driver updateDriver(
            Long id,
            Driver updatedDriver
    ) {

        Driver driver = getDriver(id);

        driver.setName(updatedDriver.getName());
        driver.setPhone(updatedDriver.getPhone());
        driver.setLicenseNumber(
                updatedDriver.getLicenseNumber()
        );
        driver.setAvailable(
                updatedDriver.isAvailable()
        );

        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}