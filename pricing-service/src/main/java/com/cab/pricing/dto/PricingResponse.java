package com.cab.pricing.dto;

// DTO for pricing response
// contains detailed breakdown of pricing calculation
//provies transparency to calling service about how price was calculated
//allows front-end applications to show price breakdown to customers
//demonstrates good API design with detailed response objects

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
    // toString Mehtod for Debugging
    //useful for logging and debugging
    //shows all price components in a readable format
    //good practice for DTO classes

    @Override
    public String toString() { return String.format("PricingResponse{totalAmount=%.2f}", totalAmount);}
}
