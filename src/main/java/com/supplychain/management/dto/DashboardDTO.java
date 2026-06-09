package com.supplychain.management.dto;

public class DashboardDTO {

    private long totalUsers;
    private long totalProducts;
    private long totalOrders;

    private long placedOrders;
    private long processingOrders;
    private long shippedOrders;
    private long deliveredOrders;
    private long cancelledOrders;

    private long totalDrivers;
    private long availableDrivers;

    private long totalVehicles;
    private long availableVehicles;

    private long activeShipments;

    private long lowStockProducts;

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getPlacedOrders() {
        return placedOrders;
    }

    public void setPlacedOrders(long placedOrders) {
        this.placedOrders = placedOrders;
    }

    public long getProcessingOrders() {
        return processingOrders;
    }

    public void setProcessingOrders(long processingOrders) {
        this.processingOrders = processingOrders;
    }

    public long getShippedOrders() {
        return shippedOrders;
    }

    public void setShippedOrders(long shippedOrders) {
        this.shippedOrders = shippedOrders;
    }

    public long getDeliveredOrders() {
        return deliveredOrders;
    }

    public void setDeliveredOrders(long deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
    }

    public long getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(long cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public long getTotalDrivers() {
        return totalDrivers;
    }

    public void setTotalDrivers(long totalDrivers) {
        this.totalDrivers = totalDrivers;
    }

    public long getAvailableDrivers() {
        return availableDrivers;
    }

    public void setAvailableDrivers(long availableDrivers) {
        this.availableDrivers = availableDrivers;
    }

    public long getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(long totalVehicles) {
        this.totalVehicles = totalVehicles;
    }

    public long getAvailableVehicles() {
        return availableVehicles;
    }

    public void setAvailableVehicles(long availableVehicles) {
        this.availableVehicles = availableVehicles;
    }

    public long getActiveShipments() {
        return activeShipments;
    }

    public void setActiveShipments(long activeShipments) {
        this.activeShipments = activeShipments;
    }

    public long getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(long lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }
// Generate getters and setters
}