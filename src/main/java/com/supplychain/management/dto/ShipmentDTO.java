package com.supplychain.management.dto;

public class ShipmentDTO {

    private Long orderId;
    private Long driverId;
    private Long vehicleId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

}