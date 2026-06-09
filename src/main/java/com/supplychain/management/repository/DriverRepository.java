package com.supplychain.management.repository;

import com.supplychain.management.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository
        extends JpaRepository<Driver, Long> {
    long countByAvailableTrue();
}