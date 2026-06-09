package com.supplychain.management.dto;

import com.supplychain.management.entity.OrderStatus;

public class OrderStatusDTO {

    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}