package com.cab.order.controller;

import com.cab.order.dto.OrderRequest;
import com.cab.order.dto.OrderResponse;
import com.cab.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    // Constructor-based dependency injection: Spring injects OrderService here
    @Autowired
    public OrderController(OrderService orderService) { this.orderService = orderService; }

    //@PostMapping: Maps HTTP Post Request
    //@RequestBody: Accepts order request data
    //demonstrates microservice communication with pricing service

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder (@Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    //Health Check endpoint
    //simple endpoint to verify serivce is running
    //important for microservice deployment and orchestration

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {return ResponseEntity.ok("Order Service is running!");}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> fieldErrors = new HashMap<>();

        for (FieldError error: e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        errorResponse.put("error", "Validation Failed");
        errorResponse.put("message", "Please check the required fields");
        errorResponse.put("fieldErrors", fieldErrors);
        errorResponse.put("timestamp", java.time.Instant.now().toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Order processing failed");
        errorResponse.put("message", "Unable to process order at this time. please try again later");
        errorResponse.put("timestamp", java.time.Instant.now().toString());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }
}
