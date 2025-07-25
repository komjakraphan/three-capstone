package com.cab.order.dto;

public class PricingResponse {
    private Double totalAmount;

    public PricingResponse(){}

    public PricingResponse(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
