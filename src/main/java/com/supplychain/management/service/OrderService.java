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
    public Order cancelOrder(Long orderId, String email) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        if (!order.getUserEmail().equals(email)) {
            throw new RuntimeException(
                    "You cannot cancel someone else's order"
            );
        }

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException(
                    "Order already cancelled"
            );
        }

        if (order.getStatus() != OrderStatus.PLACED) {
            throw new RuntimeException(
                    "Only placed orders can be cancelled"
            );
        }

        // Restore stock
        for (OrderItem item : order.getItems()) {

            Product product = productRepository.findById(
                    item.getProductId()
            ).orElseThrow();

            product.setQuantity(
                    product.getQuantity() + item.getQuantity()
            );

            productRepository.save(product);
        }

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }
    public Order updateOrderStatus(
            Long orderId,
            OrderStatus newStatus
    ) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException(
                    "Cancelled orders cannot be updated"
            );
        }

        order.setStatus(newStatus);

        return orderRepository.save(order);
    }
}