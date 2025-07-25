package com.cab.pricing.controller;

import com.cab.pricing.dto.OrderPricingRequest;
import com.cab.pricing.dto.PricingResponse;
import com.cab.pricing.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

//simplified pricing controller
//focuses on core pricing calculation functionality
//microservice communication pattern

@RestController
@RequestMapping("/api/pricing")
@Validated
public class PricingController {

    private final PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {this.pricingService = pricingService;}

    //Main pricing endpoint
    //Post /api/pricing/calculate
    //Core endpoint that other microservices will call
    //@Valid annotation triggers validation on the request body
    //return pricing calculation

    @PostMapping("/calculate")
    public ResponseEntity<PricingResponse> calculateOrderPrice(@Valid @RequestBody OrderPricingRequest request) {
        try {
            PricingResponse response = pricingService.calculateOrderPrice(request);
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            System.err.println("Error calculating price: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {return ResponseEntity.ok("Pricing Service is Running");}
}
