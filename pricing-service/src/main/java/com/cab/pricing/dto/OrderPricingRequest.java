package com.cab.pricing.dto;

public class OrderPricingRequest {
    private String customerName;
    private String cabType;
    private Integer distance;

    public OrderPricingRequest(){}

    public OrderPricingRequest(String customerName, String cabType, Integer distance) {
        this.customerName = customerName;
        this.cabType = cabType;
        this.distance = distance;
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
}
