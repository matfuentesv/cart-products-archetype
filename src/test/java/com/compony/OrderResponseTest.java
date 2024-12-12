package com.compony;

import cl.company.model.OrderResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static junit.framework.Assert.*;


class OrderResponseTest {


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
