package com.compony;

import cl.company.model.Order;
import cl.company.model.OrderDetail;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

class OrderBuilderTest {

    @Test
    void testBuilderWithUserName() {
        Order.Builder builder = new Order.Builder();
        String testUserName = "John Doe";

        builder.withUserName(testUserName);

        Order order = builder.build();
        assertNotNull(order);
        assertEquals(testUserName, order.getUserName());
    }

    @Test
    void testBuilderWithTotalAmount() {
        Order.Builder builder = new Order.Builder();
        Double testTotalAmount = 250.0;

        builder.withTotalAmount(testTotalAmount);

        Order order = builder.build();
        assertNotNull(order);
        assertEquals(testTotalAmount, order.getTotalAmount());
    }


}