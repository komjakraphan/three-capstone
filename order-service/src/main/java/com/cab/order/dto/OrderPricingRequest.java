package com.cab.order.dto;

public class OrderPricingRequest {
    private String CustomerName;
    private String cabType;
    private Integer distance;

    public OrderPricingRequest(){}

    public OrderPricingRequest(String customerName, String cabType, Integer distance) {
        CustomerName = customerName;
        this.cabType = cabType;
        this.distance = distance;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
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



