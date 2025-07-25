package com.cab.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.cab.order.dto.OrderRequest;
import com.cab.order.dto.OrderResponse;
import com.cab.order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;
    private OrderRequest orderRequest;
    private OrderResponse orderResponse;

    @BeforeEach
    void setUp() {
        orderRequest = new OrderRequest();
        orderRequest.setCustomerName("Test Customer");
        orderRequest.setCabType("Saver");
        orderRequest.setDistance(15);

        orderResponse = new OrderResponse(1L, "Test Customer", "Saver", 15, 19.80, "CONFIRMED");
    }

    @Test
    void createOrder_ShouldReturnOrderResponse_WhenValidRequestProvided() throws Exception {
        when(orderService.createOrder(any(OrderRequest.class))).thenReturn(orderResponse);

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.customerName").value("Test Customer"))
                .andExpect(jsonPath("$.cabType").value("Saver"))
                .andExpect(jsonPath("$.distance").value(15))
                .andExpect(jsonPath("$.totalPrice").value(19.80))
                .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }
}
