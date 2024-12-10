package com.compony;

import cl.company.model.Order;
import cl.company.model.OrderDetail;
import cl.company.model.OrderRequest;
import cl.company.model.OrderResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderModelTest {

    @Test
    void testOrderGettersAndSetters() {
        Order order = new Order();
        order.setId(1L);
        order.setUserName("John Doe");
        order.setTotalAmount(150.0);

        assertEquals(1L, order.getId());
        assertEquals("John Doe", order.getUserName());
        assertEquals(150.0, order.getTotalAmount());

        OrderDetail detail = new OrderDetail();
        detail.setId(1L);
        detail.setProductName("Product A");
        detail.setPrice(50.0);
        detail.setQuantity(2);
        detail.setOrder(order);

        order.setOrderDetails(Collections.singletonList(detail));

        assertNotNull(order.getOrderDetails());
        assertEquals(1, order.getOrderDetails().size());
        assertEquals("Product A", order.getOrderDetails().get(0).getProductName());
    }

    @Test
    void testOrderDetailSettersAndBuilder() {
        OrderDetail detail = new OrderDetail.Builder()
                .withProductName("Product A")
                .withPrice(50.0)
                .withQuantity(2)
                .build();

        assertNotNull(detail);
        assertEquals("Product A", detail.getProductName());
        assertEquals(50.0, detail.getPrice());
        assertEquals(2, detail.getQuantity());
    }

    @Test
    void testOrderRequestSetters() {
        OrderRequest.ProductRequest productRequest = new OrderRequest.ProductRequest();
        productRequest.setProductName("Phone");
        productRequest.setPrice(800.0);
        productRequest.setQuantity(1);

        assertEquals("Phone", productRequest.getProductName());
        assertEquals(800.0, productRequest.getPrice());
        assertEquals(1, productRequest.getQuantity());

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserName("Jane Doe");
        orderRequest.setProducts(Collections.singletonList(productRequest));

        assertNotNull(orderRequest.getProducts());
        assertEquals(1, orderRequest.getProducts().size());
        assertEquals("Jane Doe", orderRequest.getUserName());
    }

    @Test
    void testOrderResponseAndProductResponse() {
        OrderResponse.ProductResponse productResponse = new OrderResponse.ProductResponse("Tablet", 300.0, 1);
        assertNotNull(productResponse);
        assertEquals("Tablet", productResponse.getProductName());
        assertEquals(300.0, productResponse.getPrice());
        assertEquals(1, productResponse.getQuantity());

        OrderResponse orderResponse = new OrderResponse(1L, "John Doe", 300.0, Collections.singletonList(productResponse));
        assertNotNull(orderResponse);
        assertEquals(1L, orderResponse.getOrderId());
        assertEquals("John Doe", orderResponse.getUserName());
        assertEquals(300.0, orderResponse.getTotalAmount());
        assertEquals(1, orderResponse.getProducts().size());
    }

    @Test
    void testNullSafety() {
        // Testing null safety for all setters
        Order order = new Order();
        order.setUserName(null);
        order.setTotalAmount(null);
        order.setOrderDetails(null);

        assertNull(order.getUserName());
        assertNull(order.getTotalAmount());
        assertNull(order.getOrderDetails());

        OrderDetail detail = new OrderDetail();
        detail.setProductName(null);
        detail.setPrice(null);
        detail.setQuantity(null);

        assertNull(detail.getProductName());
        assertNull(detail.getPrice());
        assertNull(detail.getQuantity());

        OrderRequest.ProductRequest productRequest = new OrderRequest.ProductRequest();
        productRequest.setProductName(null);
        productRequest.setPrice(null);
        productRequest.setQuantity(null);

        assertNull(productRequest.getProductName());
        assertNull(productRequest.getPrice());
        assertNull(productRequest.getQuantity());
    }

    @Test
    void testEdgeCases() {
        OrderDetail detail = new OrderDetail.Builder()
                .withProductName("")
                .withPrice(0.0)
                .withQuantity(0)
                .build();

        assertEquals("", detail.getProductName());
        assertEquals(0.0, detail.getPrice());
        assertEquals(0, detail.getQuantity());
    }

    @Test
    void testEqualsAndHashCodeForModels() {
        Order order1 = new Order.Builder().withUserName("John").withTotalAmount(100.0).build();
        Order order2 = new Order.Builder().withUserName("John").withTotalAmount(100.0).build();

        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());

        OrderDetail detail1 = new OrderDetail.Builder().withProductName("Product").withPrice(100.0).build();
        OrderDetail detail2 = new OrderDetail.Builder().withProductName("Product").withPrice(100.0).build();

        assertEquals(detail1, detail2);
        assertEquals(detail1.hashCode(), detail2.hashCode());
    }

    @Test
    void testWithOrderDetails() {
        // Crear instancias de OrderDetail
        OrderDetail detail1 = new OrderDetail();
        detail1.setProductName("Product A");
        detail1.setPrice(100.0);
        detail1.setQuantity(2);

        OrderDetail detail2 = new OrderDetail();
        detail2.setProductName("Product B");
        detail2.setPrice(200.0);
        detail2.setQuantity(1);

        // Crear una lista de detalles
        List<OrderDetail> orderDetails = Arrays.asList(detail1, detail2);

        // Usar el builder para asignar la lista
        Order order = new Order.Builder()
                .withOrderDetails(orderDetails)
                .build();

        // Verificaciones
        assertNotNull(order);
        assertNotNull(order.getOrderDetails());
        assertEquals(2, order.getOrderDetails().size());

        // Verificar el contenido de la lista
        assertEquals("Product A", order.getOrderDetails().get(0).getProductName());
        assertEquals(100.0, order.getOrderDetails().get(0).getPrice());
        assertEquals(2, order.getOrderDetails().get(0).getQuantity());

        assertEquals("Product B", order.getOrderDetails().get(1).getProductName());
        assertEquals(200.0, order.getOrderDetails().get(1).getPrice());
        assertEquals(1, order.getOrderDetails().get(1).getQuantity());
    }
}
