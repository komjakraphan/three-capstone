package com.cab.order.dto;

public class OrderResponse {

    private Long orderId;
    private String customerName;
    private String cabType;
    private Integer distance;
    private double totalPrice;
    private String status;

    public OrderResponse() {}

    public OrderResponse(Long orderId, String customerName, String cabType, Integer distance, double totalPrice, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.cabType = cabType;
        this.distance = distance;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCabType() {
        return cabType;
    }

    public void setCabType(String cabType) {
        this.cabType = cabType;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
