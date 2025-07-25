package com.cab.order.service;

import com.cab.order.dto.OrderRequest;
import com.cab.order.dto.OrderResponse;
import com.cab.order.dto.PricingResponse;
import com.cab.order.dto.OrderPricingRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;
    private OrderRequest orderRequest;
    private PricingResponse pricingResponse;

    @BeforeEach
    void setUp(){
        ReflectionTestUtils.setField(orderService, "pricingServiceUrl", "http://localhost:8081/api/pricing/calculate");

        orderRequest = new OrderRequest();
        orderRequest.setCustomerName("Test Customer");
        orderRequest.setCabType("Priority Luxury");
        orderRequest.setDistance(3);

        pricingResponse = new PricingResponse();
        pricingResponse.setTotalAmount(9.90);
    }

    @Test
    void createOrder_ShouldReturnOrderWithCalculatedPrice_WhenPricingServieReturnPrice(){
        ResponseEntity<PricingResponse> responseEntity = new ResponseEntity<>(pricingResponse, HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), eq(PricingResponse.class)))
                .thenReturn(responseEntity);

        OrderResponse result = orderService.createOrder(orderRequest);

        assertNotNull(result);
        assertEquals(1L, result.getOrderId());
        assertEquals("Test Customer", result.getCustomerName());
        assertEquals("Priority Luxury", result.getCabType());
        assertEquals(3, result.getDistance());
        assertEquals(9.90, result.getTotalPrice());
        assertEquals("CONFIRMED", result.getStatus());

        verify(restTemplate, times(1)).postForEntity(anyString(), any(HttpEntity.class), eq(PricingResponse.class));
    }

}
