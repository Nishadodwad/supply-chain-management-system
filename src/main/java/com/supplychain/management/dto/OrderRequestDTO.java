package com.supplychain.management.dto;

import java.util.List;

public class OrderRequestDTO {

    private List<OrderItemDTO> items;

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}