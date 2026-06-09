package com.supplychain.management.repository;

import com.supplychain.management.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository
        extends JpaRepository<Location,Long> {
}