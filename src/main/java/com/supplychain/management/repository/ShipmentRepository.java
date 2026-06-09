package com.supplychain.management.repository;

import com.supplychain.management.entity.Shipment;
import com.supplychain.management.entity.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository
        extends JpaRepository<Shipment, Long> {
    long countByStatus(ShipmentStatus status);
}