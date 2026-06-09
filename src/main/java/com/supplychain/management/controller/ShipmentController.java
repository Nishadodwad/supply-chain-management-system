package com.supplychain.management.controller;

import com.supplychain.management.dto.ShipmentDTO;
import com.supplychain.management.entity.Shipment;
import com.supplychain.management.entity.ShipmentStatus;
import com.supplychain.management.service.ShipmentService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(
            ShipmentService shipmentService
    ) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public Shipment createShipment(
            @RequestBody ShipmentDTO dto
    ) {
        return shipmentService.createShipment(dto);
    }

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @GetMapping("/{id}")
    public Shipment getShipment(
            @PathVariable Long id
    ) {
        return shipmentService.getShipment(id);
    }
    @PutMapping("/{id}/status")
    public Shipment updateShipmentStatus(
            @PathVariable Long id,
            @RequestParam ShipmentStatus status
    ) {
        return shipmentService.updateShipmentStatus(id, status);
    }
    @GetMapping("/my-shipments")
    public List<Shipment> getMyShipments(
            Authentication auth
    ) {
        return shipmentService.getMyShipments(
                auth.getName()
        );
    }
}