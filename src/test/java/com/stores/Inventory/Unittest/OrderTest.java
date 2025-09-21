package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void testDefaultStatusIsPending() {
        assertEquals(OrderStatus.PENDING, order.getStatus());
    }

    @Test
    void testCreatedAtIsInitialized() {
        assertNotNull(order.getCreatedAt());
        assertTrue(order.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testSetAndGetId() {
        order.setId(1L);
        assertEquals(1L, order.getId());
    }

    @Test
    void testSetAndGetUser() {
        User user = new User();
        order.setUser(user);
        assertEquals(user, order.getUser());
    }

    @Test
    void testGetTotalPriceWithNullOrderItems() {
        order.setOrderItems(null);
        BigDecimal total = order.getTotalPrice();
        assertEquals(new BigDecimal("0.00"), total);
    }

    @Test
    void testGetTotalPriceWithEmptyOrderItems() {
        order.setOrderItems(Collections.emptyList());
        BigDecimal total = order.getTotalPrice();
        assertEquals(new BigDecimal("0.00"), total);
    }

    @Test
    void testGetTotalPriceWithValidItems() {
        OrderItem item1 = new OrderItem(2, new BigDecimal("10.50"), new Product(), order);
        OrderItem item2 = new OrderItem(3, new BigDecimal("5.25"), new Product(), order);

        order.setOrderItems(Arrays.asList(item1, item2));

        BigDecimal total = order.getTotalPrice();
        assertEquals(new BigDecimal("36.75"), total);
    }

    @Test
    void testGetTotalPriceSkipsNullItems() {
        OrderItem item1 = new OrderItem(2, new BigDecimal("10.00"), new Product(), order);

        order.setOrderItems(Arrays.asList(item1, null));

        BigDecimal total = order.getTotalPrice();
        assertEquals(new BigDecimal("20.00"), total);
    }

    @Test
    void testGetTotalPriceSkipsItemsWithNullPrice() {
        OrderItem item1 = new OrderItem(2, null, new Product(), order);
        OrderItem item2 = new OrderItem(1, new BigDecimal("15.00"), new Product(), order);

        order.setOrderItems(Arrays.asList(item1, item2));

        BigDecimal total = order.getTotalPrice();
        assertEquals(new BigDecimal("15.00"), total);
    }

    @Test
    void testTotalPriceIsStoredAndUpdated() {
        OrderItem item1 = new OrderItem(1, new BigDecimal("10.00"), new Product(), order);
        order.setOrderItems(Collections.singletonList(item1));

        BigDecimal firstCall = order.getTotalPrice();
        assertEquals(new BigDecimal("10.00"), firstCall);

        // Update items and recalc
        item1.setQuantity(3);
        BigDecimal updatedCall = order.getTotalPrice();
        assertEquals(new BigDecimal("30.00"), updatedCall);
    }
}
