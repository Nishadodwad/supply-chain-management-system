package com.supplychain.management.repository;

import com.supplychain.management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    List<Product> findByNameContainingIgnoreCase(String keyword);
}