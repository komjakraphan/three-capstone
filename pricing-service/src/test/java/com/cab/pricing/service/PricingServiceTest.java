package com.cab.pricing.service;

import com.cab.pricing.dto.OrderPricingRequest;
import com.cab.pricing.dto.PricingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {
    @InjectMocks
    private PricingService pricingService;

    private OrderPricingRequest pricingRequest;

    @BeforeEach
    void setUp(){
        pricingRequest = new OrderPricingRequest();
        pricingRequest.setCustomerName("Test Customer");
        pricingRequest.setDistance(5);
    }

    @Test
    void calculateOrderPrice_ShouldReturnBasePrice_WhenStandardCabType(){
        pricingRequest.setCabType("Standard");

        PricingResponse result = pricingService.calculateOrderPrice(pricingRequest);

        assertNotNull(result);
        double expectedBasePrice = 5 * 1.5;
        double expectedComplexityFactor = 1;
        double expectedAdjustedPrice = expectedBasePrice * expectedComplexityFactor;
        double expectedTax = expectedAdjustedPrice * .1;
        double expectedTotal = expectedAdjustedPrice + expectedTax;

        assertEquals(expectedTotal, result.getTotalAmount(), 0.01);
        assertTrue(result.getTotalAmount() > 0);
    }
}
