package com.supplychain.management.service;

import com.supplychain.management.dto.OrderRequestDTO;
import com.supplychain.management.dto.OrderItemDTO;
import com.supplychain.management.entity.*;
import com.supplychain.management.repository.OrderRepository;
import com.supplychain.management.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.supplychain.management.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order placeOrder(OrderRequestDTO request, Authentication auth) {

        Order order = new Order();
        order.setUserEmail(auth.getName());
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (OrderItemDTO dto : request.getItems()) {

            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < dto.getQuantity()) {
                throw new RuntimeException("Not enough stock for " + product.getName());
            }

            // reduce stock
            product.setQuantity(product.getQuantity() - dto.getQuantity());

            OrderItem item = new OrderItem();
            item.setProductId(product.getId());
            item.setQuantity(dto.getQuantity());
            item.setPrice(product.getPrice());
            item.setOrder(order);

            total += product.getPrice() * dto.getQuantity();
            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    public Order getMyOrder(Long orderId, String email) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        if (!order.getUserEmail().equals(email)) {
            throw new RuntimeException(
                    "You are not allowed to view this order"
            );
        }

        return order;
    }
    public List<Order> getMyOrders(String email) {
        return orderRepository.findByUserEmail(email);
    }
}