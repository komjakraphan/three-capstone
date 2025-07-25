package com.cab.order.service;

import com.cab.order.dto.OrderRequest;
import com.cab.order.dto.OrderResponse;
import com.cab.order.dto.OrderPricingRequest;
import com.cab.order.dto.PricingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {
    private final RestTemplate restTemplate;
    //thread-safe map to store orders; allows safe concurrent access by multiple threads
    private final Map<Long, OrderResponse> orders = new ConcurrentHashMap<>();
    private final AtomicLong orderIdCounter = new AtomicLong(1);

    // The pricing service URL is now injected form configuration for flexibility and best practice
    @Value("${pricing.service-url}")
    private String pricingServiceUrl;

    @Autowired
    public OrderService(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    //Main order creation method
    //creates an order and calculate pricing through microservice call
    //demonstrates the core pattern: order creation -> pricing calculation

    public OrderResponse createOrder(OrderRequest request) {
        Long orderId = orderIdCounter.getAndIncrement();

        //create pricing request
        OrderPricingRequest pricingRequest = new OrderPricingRequest();
        pricingRequest.setCustomerName(request.getCustomerName());
        pricingRequest.setCabType(request.getCabType());
        pricingRequest.setDistance(request.getDistance());

        //call pricing service to calculate total using RestTemplate
        //Best practice: Use configuration for service URLs instead of hardcoding
        PricingResponse pricingResponse = null;
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<OrderPricingRequest> entity = new HttpEntity<>(pricingRequest, headers);
            ResponseEntity<PricingResponse> response = restTemplate.postForEntity(pricingServiceUrl, entity, PricingResponse.class);
            pricingResponse = response.getBody();
        } catch (RestClientException e) {
            //log the error for debugging
            System.err.println("Pricing service unavailable: " + e.getMessage());
            //continue with order creation using fallback pricing
        }

        //create order response
        String status = pricingResponse != null ? "CONFIRMED" : "PENDING_PRICING";
        double totalAmount = pricingResponse != null ? pricingResponse.getTotalAmount() : 0;

        OrderResponse orderResponse = new OrderResponse(
                orderId,
                request.getCustomerName(),
                request.getCabType(),
                request.getDistance(),
                totalAmount,
                status
        );

        //store the order
        orders.put(orderId, orderResponse);
        return orderResponse;
    }

}
