package com.compony;

import cl.company.model.OrderResponse;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OrderResponseTest {

    @Test
    void testEqualsAndHashCode() {
        OrderResponse.ProductResponse productResponse1 = new OrderResponse.ProductResponse("Tablet", 300.0, 1);
        OrderResponse.ProductResponse productResponse2 = new OrderResponse.ProductResponse("Tablet", 300.0, 1);
        OrderResponse.ProductResponse productResponse3 = new OrderResponse.ProductResponse("Laptop", 500.0, 1);

        OrderResponse orderResponse1 = new OrderResponse(1L, "John Doe", 300.0, Collections.singletonList(productResponse1));
        OrderResponse orderResponse2 = new OrderResponse(1L, "John Doe", 300.0, Collections.singletonList(productResponse2));
        OrderResponse orderResponse3 = new OrderResponse(2L, "Jane Doe", 500.0, Collections.singletonList(productResponse3));

        // equals
        assertEquals(orderResponse1, orderResponse2);
        assertNotEquals(orderResponse1, orderResponse3);

        // hashCode
        assertEquals(orderResponse1.hashCode(), orderResponse2.hashCode());
        assertNotEquals(orderResponse1.hashCode(), orderResponse3.hashCode());
    }

    @Test
    void testToString() {
        OrderResponse.ProductResponse productResponse = new OrderResponse.ProductResponse("Tablet", 300.0, 1);
        OrderResponse orderResponse = new OrderResponse(1L, "John Doe", 300.0, Collections.singletonList(productResponse));

        String expected = "OrderResponse(orderId=1, userName=John Doe, totalAmount=300.0, products=[OrderResponse.ProductResponse(productName=Tablet, price=300.0, quantity=1)])";
        assertEquals(expected, orderResponse.toString());
    }



    @Test
    void testSetUserName() {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setUserName("John Doe");

        assertNotNull(orderResponse);
        assertEquals("John Doe", orderResponse.getUserName());
    }

    @Test
    void testSetTotalAmount() {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTotalAmount(150.75);

        assertNotNull(orderResponse);
        assertEquals(150.75, orderResponse.getTotalAmount());
    }

    @Test
    void testSetOrderId() {
        OrderResponse orderResponse = new OrderResponse();
        Long testOrderId = 123L;

        // Act
        orderResponse.setOrderId(testOrderId);

        // Assert
        assertNotNull(orderResponse);
        assertEquals(testOrderId, orderResponse.getOrderId());
    }

    @Test
    void testSetProducts() {
        OrderResponse.ProductResponse productResponse = new OrderResponse.ProductResponse("Laptop", 1200.0, 2);
        OrderResponse orderResponse = new OrderResponse();

        // Act
        orderResponse.setProducts(Collections.singletonList(productResponse));

        // Assert
        assertNotNull(orderResponse);
        assertNotNull(orderResponse.getProducts());
        assertEquals(1, orderResponse.getProducts().size());
        assertEquals("Laptop", orderResponse.getProducts().get(0).getProductName());
        assertEquals(1200.0, orderResponse.getProducts().get(0).getPrice());
    }

}
