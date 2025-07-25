package com.cab.order;

import com.cab.order.dto.OrderRequest;
import com.cab.order.dto.OrderResponse;
import com.cab.order.dto.PricingResponse;
import com.cab.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private OrderService orderService;

	@MockBean
	private RestTemplate restTemplate;

	@Test
	void createOrder_ShouldWork() {
		PricingResponse pricingResponse = new PricingResponse();
		pricingResponse.setTotalAmount(6.60);
		ResponseEntity<PricingResponse> responseEntity = ResponseEntity.ok(pricingResponse);

		when(restTemplate.postForEntity(anyString(), any(), eq(PricingResponse.class)))
				.thenReturn(responseEntity);

		OrderRequest request = new OrderRequest();
		request.setCustomerName("Test Customer");
		request.setCabType("Standard");
		request.setDistance(4);

		OrderResponse response = orderService.createOrder(request);

		assertNotNull(response);
		assertEquals("Test Customer", response.getCustomerName());
		assertEquals("Standard", response.getCabType());
		assertEquals(4, response.getDistance());
		assertEquals(6.60, response.getTotalPrice());
		assertEquals("CONFIRMED", response.getStatus());
	}

}
