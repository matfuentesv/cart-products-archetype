package com.compony;

import cl.company.model.*;
import cl.company.repository.OrderDetailRepository;
import cl.company.repository.OrderRepository;
import cl.company.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrder_Successful() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserName("John Doe");
        OrderRequest.ProductRequest product = new OrderRequest.ProductRequest();
        product.setProductName("Product 1");
        product.setPrice(10.0);
        product.setQuantity(2);
        orderRequest.setProducts(Collections.singletonList(product));

        Order order = new Order.Builder()
                .withUserName(orderRequest.getUserName())
                .withTotalAmount(20.0)
                .build();

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderService.createOrder(orderRequest);

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderDetailRepository, times(1)).save(any(OrderDetail.class));
    }

    @Test
    void getAllOrders_ReturnsOrders() {
        Order order = new Order();
        order.setId(1L);
        order.setUserName("John Doe");
        order.setTotalAmount(100.0);

        OrderDetail detail = new OrderDetail();
        detail.setProductName("Product 1");
        detail.setPrice(50.0);
        detail.setQuantity(2);

        order.setOrderDetails(Collections.singletonList(detail));

        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));

        List<OrderResponse> responses = orderService.getAllOrders();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("John Doe", responses.get(0).getUserName());
        verify(orderRepository, times(1)).findAll();
    }
}
