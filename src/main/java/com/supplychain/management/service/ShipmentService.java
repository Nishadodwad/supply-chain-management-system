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

    public ShipmentService(
            ShipmentRepository shipmentRepository,
            OrderRepository orderRepository
    ) {
        this.shipmentRepository = shipmentRepository;
        this.orderRepository = orderRepository;
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
        shipment.setDriverName(dto.getDriverName());
        shipment.setVehicleNumber(dto.getVehicleNumber());
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