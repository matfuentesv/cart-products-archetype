package com.compony;

import cl.company.model.OrderResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    @Test
    public void testOrderResponseConstructorAndGetters() {
        List<OrderResponse.ProductResponse> products = new ArrayList<>();
        products.add(new OrderResponse.ProductResponse("Product1", 10.0, 2));

        OrderResponse response = new OrderResponse(1L, "testUser", 20.0, products);

        assertEquals(Long.valueOf(1L), response.getOrderId());
        assertEquals("testUser", response.getUserName());
        assertEquals(20.0, response.getTotalAmount());
        assertNotNull(response.getProducts());
        assertEquals(1, response.getProducts().size());
        assertEquals("Product1", response.getProducts().get(0).getProductName());
    }

    @Test
    public void testProductResponseConstructorAndGetters() {
        OrderResponse.ProductResponse product = new OrderResponse.ProductResponse("Product1", 10.0, 2);

        assertEquals("Product1", product.getProductName());
        assertEquals(10.0, product.getPrice());
        assertEquals(2, product.getQuantity().intValue()); // Usamos .intValue() para evitar la ambigüedad
    }


    @Test
    public void testOrderResponseEqualsAndHashCode() {
        List<OrderResponse.ProductResponse> products1 = new ArrayList<>();
        products1.add(new OrderResponse.ProductResponse("Product1", 10.0, 2));
        List<OrderResponse.ProductResponse> products2 = new ArrayList<>();
        products2.add(new OrderResponse.ProductResponse("Product2", 20.0, 1));

        OrderResponse response1 = new OrderResponse(1L, "testUser", 20.0, products1);
        OrderResponse response2 = new OrderResponse(1L, "testUser", 20.0, products1);
        OrderResponse response3 = new OrderResponse(2L, "anotherUser", 40.0, products2);

        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1.hashCode(), response3.hashCode());
    }

    @Test
    public void testProductResponseEqualsAndHashCode() {
        OrderResponse.ProductResponse product1 = new OrderResponse.ProductResponse("Product1", 10.0, 2);
        OrderResponse.ProductResponse product2 = new OrderResponse.ProductResponse("Product1", 10.0, 2);
        OrderResponse.ProductResponse product3 = new OrderResponse.ProductResponse("Product2", 20.0, 1);

        assertEquals(product1, product2);
        assertNotEquals(product1, product3);
        assertEquals(product1.hashCode(), product2.hashCode());
        assertNotEquals(product1.hashCode(), product3.hashCode());
    }

    @Test
    public void testOrderResponseToString() {
        List<OrderResponse.ProductResponse> products = new ArrayList<>();
        products.add(new OrderResponse.ProductResponse("Product1", 10.0, 2));

        OrderResponse response = new OrderResponse(1L, "testUser", 20.0, products);
        String toString = response.toString();

        assertTrue(toString.contains("testUser"));
        assertTrue(toString.contains("20.0"));
        assertTrue(toString.contains("Product1"));
    }

    @Test
    public void testProductResponseToString() {
        OrderResponse.ProductResponse product = new OrderResponse.ProductResponse("Product1", 10.0, 2);
        String toString = product.toString();

        assertTrue(toString.contains("Product1"));
        assertTrue(toString.contains("10.0"));
        assertTrue(toString.contains("2"));
    }

    @Test
    public void testSetProductName() {
        // Crear instancia de ProductResponse
        OrderResponse.ProductResponse product = new OrderResponse.ProductResponse(null, null, null);

        // Establecer un valor para productName
        product.setProductName("New Product");

        // Verificar el valor establecido
        assertEquals("New Product", product.getProductName());
    }

    @Test
    public void testSetPrice() {
        // Crear instancia de ProductResponse
        OrderResponse.ProductResponse product = new OrderResponse.ProductResponse(null, null, null);

        // Establecer un valor para price
        product.setPrice(99.99);

        // Verificar el valor establecido
        assertEquals(99.99, product.getPrice());
    }

    @Test
    public void testSetQuantity() {
        // Crear instancia de ProductResponse
        OrderResponse.ProductResponse product = new OrderResponse.ProductResponse(null, null, null);

        // Establecer un valor para quantity
        product.setQuantity(10);

        // Verificar el valor establecido
        assertEquals(10, product.getQuantity().intValue());
    }


}
