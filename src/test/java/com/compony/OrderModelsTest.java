package com.compony;

import cl.company.model.Order;
import cl.company.model.OrderDetail;
import cl.company.model.OrderRequest;
import cl.company.model.OrderResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

 class OrderModelsTest {

    @Test
     void testOrderGettersAndSetters() {
        Order order = new Order();
        order.setId(1L);
        order.setUserName("John Doe");
        order.setTotalAmount(150.0);
        order.setCreatedAt("2024-12-01T10:00:00");

        assertEquals(1L, order.getId());
        assertEquals("John Doe", order.getUserName());
        assertEquals(150.0, order.getTotalAmount());
        assertEquals("2024-12-01T10:00:00", order.getCreatedAt());
    }

    @Test
     void testOrderDetailGettersAndSetters() {
        Order order = new Order();
        OrderDetail detail = new OrderDetail();
        detail.setId(1L);
        detail.setOrder(order);
        detail.setProductName("Product A");
        detail.setPrice(50.0);
        detail.setQuantity(2);

        assertEquals(1L, detail.getId());
        assertEquals(order, detail.getOrder());
        assertEquals("Product A", detail.getProductName());
        assertEquals(50.0, detail.getPrice());
        assertEquals(2, detail.getQuantity());
    }

    @Test
     void testOrderBuilder() {
        OrderDetail detail = new OrderDetail.Builder()
                .withProductName("Product A")
                .withPrice(50.0)
                .withQuantity(2)
                .build();

        Order order = new Order.Builder()
                .withUserName("John Doe")
                .withTotalAmount(150.0)
                .withOrderDetails(Collections.singletonList(detail))
                .build();

        assertEquals("John Doe", order.getUserName());
        assertEquals(150.0, order.getTotalAmount());
        assertNotNull(order.getOrderDetails());
        assertEquals(1, order.getOrderDetails().size());
        assertEquals("Product A", order.getOrderDetails().get(0).getProductName());
    }

    @Test
     void testOrderEqualsAndHashCode() {
        Order order1 = new Order(1L, "John Doe", 150.0, "2024-12-01T10:00:00", null);
        Order order2 = new Order(1L, "John Doe", 150.0, "2024-12-01T10:00:00", null);
        Order order3 = new Order(2L, "Jane Doe", 200.0, "2024-12-02T10:00:00", null);

        assertEquals(order1, order2);
        assertNotEquals(order1, order3);
        assertEquals(order1.hashCode(), order2.hashCode());
        assertNotEquals(order1.hashCode(), order3.hashCode());
    }

    @Test
     void testOrderToString() {
        Order order = new Order(1L, "John Doe", 150.0, "2024-12-01T10:00:00", new ArrayList<>());
        String toStringOutput = order.toString();

        assertTrue(toStringOutput.contains("John Doe"));
        assertTrue(toStringOutput.contains("150.0"));
    }

    @Test
     void testOrderRequestGettersAndSetters() {
        OrderRequest.ProductRequest productRequest = new OrderRequest.ProductRequest();
        productRequest.setProductName("Product A");
        productRequest.setPrice(100.0);
        productRequest.setQuantity(2);

        assertEquals("Product A", productRequest.getProductName());
        assertEquals(100.0, productRequest.getPrice());
        assertEquals(2, productRequest.getQuantity());

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserName("John Doe");
        orderRequest.setProducts(Collections.singletonList(productRequest));

        assertEquals("John Doe", orderRequest.getUserName());
        assertNotNull(orderRequest.getProducts());
        assertEquals(1, orderRequest.getProducts().size());
    }

    @Test
     void testOrderResponseGettersAndSetters() {
        OrderResponse.ProductResponse productResponse = new OrderResponse.ProductResponse("Product A", 100.0, 2);
        assertEquals("Product A", productResponse.getProductName());
        assertEquals(100.0, productResponse.getPrice());
        assertEquals(2, productResponse.getQuantity());

        OrderResponse orderResponse = new OrderResponse(1L, "John Doe", 150.0, Collections.singletonList(productResponse));
        assertEquals(1L, orderResponse.getOrderId());
        assertEquals("John Doe", orderResponse.getUserName());
        assertEquals(150.0, orderResponse.getTotalAmount());
        assertNotNull(orderResponse.getProducts());
        assertEquals(1, orderResponse.getProducts().size());
    }

    @Test
     void testToStringMethods() {
        Order order = new Order(1L, "John Doe", 150.0, "2024-12-01T10:00:00", new ArrayList<>());
        assertTrue(order.toString().contains("John Doe"));
        assertTrue(order.toString().contains("150.0"));

        OrderDetail detail = new OrderDetail(1L, order, "Product A", 50.0, 2);
        assertTrue(detail.toString().contains("Product A"));
        assertTrue(detail.toString().contains("50.0"));

        OrderResponse.ProductResponse productResponse = new OrderResponse.ProductResponse("Product A", 100.0, 2);
        assertTrue(productResponse.toString().contains("Product A"));
        assertTrue(productResponse.toString().contains("100.0"));
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

    @Test
     void testEquals_SameObject() {
        OrderDetail detail = new OrderDetail(1L, null, "Product A", 50.0, 2);
        assertEquals(detail, detail); // Comparación con el mismo objeto
    }

    @Test
     void testEquals_NullObject() {
        OrderDetail detail = new OrderDetail(1L, null, "Product A", 50.0, 2);
        assertNotEquals(null, detail); // Comparación con null
    }

    @Test
     void testEquals_DifferentClass() {
        OrderDetail detail = new OrderDetail(1L, null, "Product A", 50.0, 2);
        assertNotEquals("Not an OrderDetail", detail); // Comparación con otra clase
    }

    @Test
     void testEquals_EqualObjects() {
        OrderDetail detail1 = new OrderDetail(1L, null, "Product A", 50.0, 2);
        OrderDetail detail2 = new OrderDetail(1L, null, "Product A", 50.0, 2);

        assertTrue(detail1.equals(detail2));
        assertEquals(detail1.hashCode(), detail2.hashCode());
    }

    @Test
     void testEquals_DifferentId() {
        OrderDetail detail1 = new OrderDetail(1L, null, "Product A", 50.0, 2);
        OrderDetail detail2 = new OrderDetail(2L, null, "Product A", 50.0, 2);

        assertNotEquals(detail1, detail2); // IDs diferentes
    }

    @Test
     void testEquals_DifferentProductName() {
        OrderDetail detail1 = new OrderDetail(1L, null, "Product A", 50.0, 2);
        OrderDetail detail2 = new OrderDetail(1L, null, "Product B", 50.0, 2);

        assertNotEquals(detail1, detail2); // Nombres de producto diferentes
    }

    @Test
     void testEquals_DifferentPrice() {
        OrderDetail detail1 = new OrderDetail(1L, null, "Product A", 50.0, 2);
        OrderDetail detail2 = new OrderDetail(1L, null, "Product A", 60.0, 2);

        assertNotEquals(detail1, detail2); // Precios diferentes
    }

    @Test
     void testEquals_DifferentQuantity() {
        OrderDetail detail1 = new OrderDetail(1L, null, "Product A", 50.0, 2);
        OrderDetail detail2 = new OrderDetail(1L, null, "Product A", 50.0, 3);

        assertNotEquals(detail1, detail2); // Cantidades diferentes
    }

    @Test
     void testHashCode_Consistency() {
        OrderDetail detail = new OrderDetail(1L, null, "Product A", 50.0, 2);

        int initialHashCode = detail.hashCode();
        int repeatedHashCode = detail.hashCode();

        assertEquals(initialHashCode, repeatedHashCode); // Consistencia de hashCode
    }

    @Test
     void testHashCode_DifferentObjects() {
        OrderDetail detail1 = new OrderDetail(1L, null, "Product A", 50.0, 2);
        OrderDetail detail2 = new OrderDetail(2L, null, "Product B", 60.0, 3);

        assertNotEquals(detail1.hashCode(), detail2.hashCode());
    }

     @Test
      void testEquals_Null() {
         OrderDetail detail = new OrderDetail(1L, null, "Product A", 50.0, 2);
         assertNotEquals(null, detail); // Comparación con null
     }


     @Test
      void testEquals_Values() {
         OrderDetail detail1 = new OrderDetail(1L, null, "Product A", 50.0, 2);
         OrderDetail detail2 = new OrderDetail(1L, null, "Product A", 50.0, 2);
         OrderDetail detail3 = new OrderDetail(2L, null, "Product B", 60.0, 3);

         assertTrue(detail1.equals(detail2)); // Objetos iguales
         assertFalse(detail1.equals(detail3)); // Objetos diferentes
     }

     @Test
      void testToString_OrderDetail() {
         OrderDetail detail = new OrderDetail(1L, null, "Product A", 50.0, 2);
         String output = detail.toString();

         assertTrue(output.contains("Product A"));
         assertTrue(output.contains("50.0"));
         assertTrue(output.contains("2"));
     }



     @Test
     public void testProductRequestEqualsAndHashCode() {
         OrderRequest.ProductRequest product1 = new OrderRequest.ProductRequest();
         product1.setProductName("Product A");
         product1.setPrice(100.0);
         product1.setQuantity(2);

         OrderRequest.ProductRequest product2 = new OrderRequest.ProductRequest();
         product2.setProductName("Product A");
         product2.setPrice(100.0);
         product2.setQuantity(2);

         OrderRequest.ProductRequest product3 = new OrderRequest.ProductRequest();
         product3.setProductName("Product B");
         product3.setPrice(200.0);
         product3.setQuantity(3);

         assertEquals(product1, product2); // Objetos iguales
         assertNotEquals(product1, product3); // Objetos diferentes
         assertEquals(product1.hashCode(), product2.hashCode()); // HashCodes iguales
         assertNotEquals(product1.hashCode(), product3.hashCode()); // HashCodes diferentes
     }

     @Test
     public void testProductRequestToString() {
         OrderRequest.ProductRequest product = new OrderRequest.ProductRequest();
         product.setProductName("Product A");
         product.setPrice(100.0);
         product.setQuantity(2);

         String output = product.toString();
         assertTrue(output.contains("Product A"));
         assertTrue(output.contains("100.0"));
         assertTrue(output.contains("2"));
     }


 }
