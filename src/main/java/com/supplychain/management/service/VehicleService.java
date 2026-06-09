package com.supplychain.management.service;

import com.supplychain.management.entity.Vehicle;
import com.supplychain.management.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(
            VehicleRepository vehicleRepository
    ) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));
    }

    public Vehicle updateVehicle(
            Long id,
            Vehicle updatedVehicle
    ) {

        Vehicle vehicle = getVehicle(id);

        vehicle.setVehicleNumber(
                updatedVehicle.getVehicleNumber()
        );
        vehicle.setType(
                updatedVehicle.getType()
        );
        vehicle.setCapacity(
                updatedVehicle.getCapacity()
        );
        vehicle.setAvailable(
                updatedVehicle.isAvailable()
        );

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}