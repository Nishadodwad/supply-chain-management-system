package com.supplychain.management.controller;

import com.supplychain.management.dto.OrderRequestDTO;
import com.supplychain.management.dto.OrderStatusDTO;
import com.supplychain.management.entity.Order;
import com.supplychain.management.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(
            @RequestBody OrderRequestDTO request,
            Authentication auth
    ) {
        return orderService.placeOrder(request, auth);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/my-orders/{id}")
    public Order getMyOrder(
            @PathVariable Long id,
            Authentication auth
    ) {
        return orderService.getMyOrder(
                id,
                auth.getName()
        );
    }
    @GetMapping("/my-orders")
    public List<Order> getMyOrders(Authentication auth) {
        return orderService.getMyOrders(auth.getName());
    }
    @PutMapping("/cancel/{id}")
    public Order cancelOrder(
            @PathVariable Long id,
            Authentication auth
    ) {
        return orderService.cancelOrder(
                id,
                auth.getName()
        );
    }
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusDTO dto
    ) {

        return orderService.updateOrderStatus(
                id,
                dto.getStatus()
        );
    }
}