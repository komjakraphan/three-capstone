package com.cab.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderRequest {
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Cab type is required")
    private String cabType;

    @NotNull(message = "Distance of ride required")
    @Positive(message = "Distance must be positive")
    private Integer distance;

    public OrderRequest() {}

    public OrderRequest(String customerName, String cabType, Integer distance) {
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
