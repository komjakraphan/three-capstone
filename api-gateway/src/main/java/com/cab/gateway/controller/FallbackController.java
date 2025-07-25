package com.cab.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//Circuit breaker fallback controller
@RestController
public class FallbackController {

    //Order Service Fallback
    //When Order Service is down it provides a user-friendly message
    @RequestMapping("/fallback/orders")
    public ResponseEntity<Map<String, Object>> orderServiceFallback() {
        return createFallbackResponse(
                "Order Servie Temporarily Unavailable",
                "We're experiencing high demand. Please place your order again in a few moments.",
                "ORDER_SERVICE_DOWN"
        );
    }
    // Pricing Service Fallback
    //When Pricing service is down it provides a user-friendly message
    @RequestMapping("/fallback/pricing")
    public ResponseEntity<Map<String, Object>> pricingServiceFallback() {
        return createFallbackResponse(
                "Pricing Service Temporarily Unavailable",
                "Unable to calculate order prices right now. Try again shortly.",
                "PRICING_SERVICE_DOWN"
        );
    }

    private ResponseEntity<Map<String, Object>> createFallbackResponse(String title, String message, String errorCode){
        Map<String, Object> response = new HashMap<>();
        response.put("error", true);
        response.put("title", title);
        response.put("message", message);
        response.put("errorCode", errorCode);
        response.put("timestamp", LocalDateTime.now());
        response.put("suggestion", "This is a temporary issue. The service will be restord shortly.");

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);

    }
}
