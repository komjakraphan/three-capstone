package com.cab.pricing;

import com.cab.pricing.controller.PricingController;
import com.cab.pricing.dto.OrderPricingRequest;
import com.cab.pricing.dto.PricingResponse;
import com.cab.pricing.service.PricingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class PricingServiceApplicationTests {

	private PricingController pricingController;
	private PricingService pricingService;

	@BeforeEach

	void setUp() {
		pricingService = new PricingService();
		pricingController = new PricingController(pricingService);
	}

	@Test
	void endToEndPricingFlow_ShouldWorkCorrectly() {
		OrderPricingRequest request = new OrderPricingRequest();
		request.setCustomerName("Test Customer");
		request.setCabType("Luxury Priority");
		request.setDistance(8);

		ResponseEntity<PricingResponse> response = pricingController.calculateOrderPrice(request);

		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());

		double totalAmount = response.getBody().getTotalAmount();
		assertTrue(totalAmount > 0, "Total amount should be positive");
		assertTrue(totalAmount > 8, "Total for a distance of 8 miles should be more");

		double expectedBasePrice = 8 * 1.5;
		assertTrue(totalAmount > expectedBasePrice, "Total shoudld be more than the base price with tax");
	}
}
