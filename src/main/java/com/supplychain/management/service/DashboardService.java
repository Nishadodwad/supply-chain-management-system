package com.supplychain.management.service;

import com.supplychain.management.dto.DashboardDTO;
import com.supplychain.management.entity.*;
import com.supplychain.management.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ShipmentRepository shipmentRepository;

    public DashboardService(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            DriverRepository driverRepository,
            VehicleRepository vehicleRepository,
            ShipmentRepository shipmentRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public DashboardDTO getDashboard() {

        DashboardDTO dto = new DashboardDTO();

        dto.setTotalUsers(userRepository.count());

        dto.setTotalProducts(productRepository.count());

        dto.setTotalOrders(orderRepository.count());

        dto.setPlacedOrders(
                orderRepository.countByStatus(OrderStatus.PLACED)
        );

        dto.setProcessingOrders(
                orderRepository.countByStatus(OrderStatus.PROCESSING)
        );

        dto.setShippedOrders(
                orderRepository.countByStatus(OrderStatus.SHIPPED)
        );

        dto.setDeliveredOrders(
                orderRepository.countByStatus(OrderStatus.DELIVERED)
        );

        dto.setCancelledOrders(
                orderRepository.countByStatus(OrderStatus.CANCELLED)
        );

        dto.setTotalDrivers(
                driverRepository.count()
        );

        dto.setAvailableDrivers(
                driverRepository.countByAvailableTrue()
        );

        dto.setTotalVehicles(
                vehicleRepository.count()
        );

        dto.setAvailableVehicles(
                vehicleRepository.countByAvailableTrue()
        );

        dto.setActiveShipments(
                shipmentRepository.countByStatus(
                        ShipmentStatus.IN_TRANSIT
                )
        );

        dto.setLowStockProducts(
                productRepository.countByQuantityLessThan(10)
        );

        return dto;
    }
}