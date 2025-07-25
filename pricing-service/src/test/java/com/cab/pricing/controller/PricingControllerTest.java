package com.cab.pricing.controller;

import com.cab.pricing.dto.OrderPricingRequest;
import com.cab.pricing.dto.PricingResponse;
import com.cab.pricing.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class PricingControllerTest {

    private PricingController pricingController;
    private PricingService pricingService;
    private OrderPricingRequest pricingRequest;

    @BeforeEach
    void setUp() {
        pricingService = new PricingService();
        pricingController = new PricingController(pricingService);

        pricingRequest = new OrderPricingRequest();
        pricingRequest.setCustomerName("Test Customer");
        pricingRequest.setCabType("Priority");
        pricingRequest.setDistance(5);
    }

    @Test
    void calculateOrderPrice_ShouldReturnOkResponse_WhenValidRequestProvided() {
        ResponseEntity<PricingResponse> response = pricingController.calculateOrderPrice(pricingRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getTotalAmount() > 0);
    }
}
