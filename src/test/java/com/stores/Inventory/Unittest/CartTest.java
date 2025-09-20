package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.Cart;
import com.stores.Inventory.model.Order;
import com.stores.Inventory.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartTest {

    private Cart cart;

    @Mock
    private Product product1, product2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void testAddProduct() {
        // Arrange
        when(product1.getId()).thenReturn(1L);
        when(product1.getPrice()).thenReturn(new BigDecimal("10.00"));
        when(product2.getId()).thenReturn(2L);
        when(product2.getPrice()).thenReturn(new BigDecimal("20.00"));

        // Act
        cart.addProduct(product1, 2);
        cart.addProduct(product2, 1);

        // Assert
        assertEquals(2, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
        assertEquals(new BigDecimal("10.00"), cart.getItems().get(0).getUnitPrice());
        assertEquals(new BigDecimal("30.00"), cart.getTotal());
    }

    @Test
    void testAddSameProductIncrementsQuantity() {
        when(product1.getId()).thenReturn(1L);
        when(product1.getPrice()).thenReturn(new BigDecimal("10.00"));

        cart.addProduct(product1, 2);
        cart.addProduct(product1, 3);

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getQuantity());
        assertEquals(new BigDecimal("50.00"), cart.getTotal());
    }

    @Test
    void testUpdateQuantity() {
        when(product1.getId()).thenReturn(1L);
        when(product1.getPrice()).thenReturn(new BigDecimal("10.00"));

        cart.addProduct(product1, 2);
        cart.updateQuantity(1L, 3);

        assertEquals(3, cart.getItems().get(0).getQuantity());
        assertEquals(new BigDecimal("30.00"), cart.getTotal());
    }

    @Test
    void testRemoveProduct() {
        when(product1.getId()).thenReturn(1L);
        when(product1.getPrice()).thenReturn(new BigDecimal("10.00"));

        cart.addProduct(product1, 2);
        cart.removeProduct(1L);

        assertTrue(cart.getItems().isEmpty());
        assertEquals(BigDecimal.ZERO, cart.getTotal());
    }

    @Test
    void testCheckoutCreatesOrderAndItems() {
        // Arrange
        when(product1.getId()).thenReturn(1L);
        when(product1.getPrice()).thenReturn(new BigDecimal("10.555"));
        when(product2.getId()).thenReturn(2L);
        when(product2.getPrice()).thenReturn(new BigDecimal("5.333"));

        cart.addProduct(product1, 2);
        cart.addProduct(product2, 3);

        // Act
        Order order = cart.checkout();

        // Assert
        assertEquals(2, order.getOrderItems().size());
        assertEquals(2, order.getOrderItems().get(0).getQuantity());
        assertEquals(new BigDecimal("10.555"), order.getOrderItems().get(0).getPrice());
        assertEquals(new BigDecimal("37.11"), order.getTotalPrice()); // As per previous calculation
        assertNotNull(order.getLocalDateTime());
    }

    @Test
    void testCheckoutWithEmptyCartThrowsException() {
        assertThrows(IllegalStateException.class, cart::checkout, "Empty cart should throw exception");
    }

    @Test
    void testAddInvalidQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(product1, 0));
    }

    @Test
    void testClearCart() {
        when(product1.getId()).thenReturn(1L);
        when(product1.getPrice()).thenReturn(new BigDecimal("10.00"));

        cart.addProduct(product1, 2);
        cart.clear();

        assertTrue(cart.getItems().isEmpty());
    }
}