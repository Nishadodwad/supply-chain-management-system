package com.supplychain.management.service;

import com.supplychain.management.dto.ShipmentDTO;
import com.supplychain.management.entity.*;
import com.supplychain.management.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public ShipmentService(
            ShipmentRepository shipmentRepository,
            OrderRepository orderRepository,
            DriverRepository driverRepository,
            VehicleRepository vehicleRepository
    ) {
        this.shipmentRepository = shipmentRepository;
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Shipment createShipment(
            ShipmentDTO dto
    ) {

        Order order = orderRepository.findById(
                dto.getOrderId()
        ).orElseThrow(() ->
                new RuntimeException("Order not found"));

        Shipment shipment = new Shipment();

        shipment.setOrderId(order.getId());
        Driver driver = driverRepository.findById(
                dto.getDriverId()
        ).orElseThrow(() ->
                new RuntimeException("Driver not found"));

        Vehicle vehicle = vehicleRepository.findById(
                dto.getVehicleId()
        ).orElseThrow(() ->
                new RuntimeException("Vehicle not found"));

        shipment.setDriver(driver);
        shipment.setVehicle(vehicle);
        driver.setAvailable(false);
        vehicle.setAvailable(false);

        driverRepository.save(driver);
        vehicleRepository.save(vehicle);
        shipment.setStatus(ShipmentStatus.PENDING);

        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipmentStatus(
            Long shipmentId,
            ShipmentStatus status
    ) {

        Shipment shipment =
                shipmentRepository.findById(shipmentId)
                        .orElseThrow(() ->
                                new RuntimeException("Shipment not found"));

        shipment.setStatus(status);

        // If shipment delivered, update order also
        if(status == ShipmentStatus.DELIVERED){

            Order order = orderRepository
                    .findById(shipment.getOrderId())
                    .orElseThrow(() ->
                            new RuntimeException("Order not found"));

            order.setStatus(OrderStatus.DELIVERED);

            orderRepository.save(order);
            shipment.getDriver().setAvailable(true);
            shipment.getVehicle().setAvailable(true);

            driverRepository.save(shipment.getDriver());
            vehicleRepository.save(shipment.getVehicle());
        }

        return shipmentRepository.save(shipment);
    }
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getShipment(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Shipment not found"));
    }
}