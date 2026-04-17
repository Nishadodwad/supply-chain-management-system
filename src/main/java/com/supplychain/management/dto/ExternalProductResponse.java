package com.supplychain.management.dto;

import java.util.List;

public class ExternalProductResponse {
    private List<ExternalProduct> products;

    public List<ExternalProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ExternalProduct> products) {
        this.products = products;
    }
}