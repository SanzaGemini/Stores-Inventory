package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.Order;
import com.stores.Inventory.model.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @InjectMocks
    private Order order;

    @Mock
    private OrderItem orderItem1;

    @Mock
    private OrderItem orderItem2;

    @BeforeEach
    void setUp() {
        order = new Order();
        // Initialize orderItems list
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        // Use reflection to set private orderItems field (since no setter exists)
        try {
            java.lang.reflect.Field field = Order.class.getDeclaredField("orderItems");
            field.setAccessible(true);
            field.set(order, orderItems);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set orderItems", e);
        }
    }

    @Test
    void testGetTotalPriceWithMultipleItems() {
        // Arrange
        when(orderItem1.getPrice()).thenReturn(new BigDecimal("10.555"));
        when(orderItem1.getQuantity()).thenReturn(2);
        when(orderItem2.getPrice()).thenReturn(new BigDecimal("5.333"));
        when(orderItem2.getQuantity()).thenReturn(3);

        // Act
        BigDecimal totalPrice = order.getTotalPrice();

        // Assert
        // Expected: (10.555 * 2) + (5.333 * 3) = 21.11 + 15.999 = 37.109
        // After rounding to 2 decimal places with CEILING: 37.11
        BigDecimal expected = new BigDecimal("37.11");
        assertEquals(expected, totalPrice, "Total price should be 37.11 after rounding");
    }

    @Test
    void testGetTotalPriceWithEmptyOrderItems() {
        // Arrange
        try {
            java.lang.reflect.Field field = Order.class.getDeclaredField("orderItems");
            field.setAccessible(true);
            field.set(order, Collections.emptyList());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set orderItems", e);
        }

        // Act
        BigDecimal totalPrice = order.getTotalPrice();

        // Assert
        // Expected: 0.00 (empty list, rounded to 2 decimal places)
        BigDecimal expected = new BigDecimal("0.00");
        assertEquals(expected, totalPrice, "Total price should be 0.00 for empty order items");
    }

    @Test
    void testGetTotalPriceWithZeroQuantity() {
        // Arrange
        when(orderItem1.getPrice()).thenReturn(new BigDecimal("10.555"));
        when(orderItem1.getQuantity()).thenReturn(0);
        when(orderItem2.getPrice()).thenReturn(new BigDecimal("5.333"));
        when(orderItem2.getQuantity()).thenReturn(2);

        // Act
        BigDecimal totalPrice = order.getTotalPrice();

        // Assert
        // Expected: (10.555 * 0) + (5.333 * 2) = 0 + 10.666 = 10.666
        // After rounding to 2 decimal places with CEILING: 10.67
        BigDecimal expected = new BigDecimal("10.67");
        assertEquals(expected, totalPrice, "Total price should be 10.67 with zero quantity for one item");
    }

    @Test
    void testGetTotalPriceWithNullPrice() {
        // Arrange
        when(orderItem1.getPrice()).thenReturn(null);
        when(orderItem1.getQuantity()).thenReturn(2);
        when(orderItem2.getPrice()).thenReturn(new BigDecimal("5.333"));
        when(orderItem2.getQuantity()).thenReturn(3);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> order.getTotalPrice(),
                "Expected NullPointerException for null price");
    }

    @Test
    void testGetTotalPriceWithNullOrderItemsList() {
        // Arrange
        try {
            java.lang.reflect.Field field = Order.class.getDeclaredField("orderItems");
            field.setAccessible(true);
            field.set(order, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set orderItems", e);
        }

        // Act
        BigDecimal totalPrice = order.getTotalPrice();

        // Assert
        // Expected: 0.00 (null list treated as empty, rounded to 2 decimal places)
        BigDecimal expected = new BigDecimal("0.00");
        assertEquals(expected, totalPrice, "Total price should be 0.00 for null order items");
    }

    @Test
    void testGetTotalPriceWithNegativeQuantity() {
        // Arrange
        when(orderItem1.getPrice()).thenReturn(new BigDecimal("10.555"));
        when(orderItem1.getQuantity()).thenReturn(-1);
        when(orderItem2.getPrice()).thenReturn(new BigDecimal("5.333"));
        when(orderItem2.getQuantity()).thenReturn(2);

        // Act
        BigDecimal totalPrice = order.getTotalPrice();

        // Assert
        // Expected: (10.555 * -1) + (5.333 * 2) = -10.555 + 10.666 = 0.111
        // After rounding to 2 decimal places with CEILING: 0.12
        BigDecimal expected = new BigDecimal("0.12");
        assertEquals(expected, totalPrice, "Total price should be 0.12 with negative quantity");
    }
}