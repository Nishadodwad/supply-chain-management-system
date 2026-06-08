package com.supplychain.management.repository;

import com.supplychain.management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserEmail(String userEmail);

}