package com.compony;

import cl.company.controller.OrderController;
import cl.company.exception.ApiResponse;
import cl.company.model.OrderRequest;
import cl.company.model.OrderResponse;
import cl.company.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_Successful() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserName("John Doe");
        orderRequest.setProducts(Collections.emptyList());

        doNothing().when(orderService).createOrder(orderRequest);

        ResponseEntity<ApiResponse> response = orderController.createOrder(orderRequest);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Orden creada con exito", response.getBody().getMessage());
        verify(orderService, times(1)).createOrder(orderRequest);
    }

    @Test
    void getAllOrders_Successful() {
        List<OrderResponse> mockOrderResponses = Collections.singletonList(
                new OrderResponse(1L, "John Doe", 100.0, Collections.emptyList())
        );

        when(orderService.getAllOrders()).thenReturn(mockOrderResponses);

        List<OrderResponse> response = orderController.getAllOrders();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("John Doe", response.get(0).getUserName());
        verify(orderService, times(1)).getAllOrders();
    }
}
