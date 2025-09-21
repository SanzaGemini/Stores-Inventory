package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.Cart;
import com.stores.Inventory.model.CartItem;
import com.stores.Inventory.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem();
    }

    @Test
    void testSetAndGetId() {
        cartItem.setId(1L);
        assertEquals(1L, cartItem.getId());
    }

    @Test
    void testSetAndGetCart() {
        Cart cart = new Cart();
        cartItem.setCart(cart);
        assertEquals(cart, cartItem.getCart());
    }

    @Test
    void testSetAndGetProduct() {
        Product product = new Product();
        cartItem.setProduct(product);
        assertEquals(product, cartItem.getProduct());
    }

    @Test
    void testSetAndGetQuantity() {
        cartItem.setQuantity(5);
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    void testSetAndGetUnitPrice() {
        BigDecimal price = new BigDecimal("49.99");
        cartItem.setUnitPrice(price);
        assertEquals(price, cartItem.getUnitPrice());
    }

    @Test
    void testGetSubtotalCalculatesCorrectly() {
        cartItem.setQuantity(3);
        cartItem.setUnitPrice(new BigDecimal("19.99"));

        BigDecimal expected = new BigDecimal("59.97");
        assertEquals(0, expected.compareTo(cartItem.getSubtotal())); // compareTo for precision safety
    }

    @Test
    void testGetSubtotalWhenQuantityIsZero() {
        cartItem.setQuantity(0);
        cartItem.setUnitPrice(new BigDecimal("100.00"));

        BigDecimal expected = BigDecimal.ZERO;
        assertEquals(0, expected.compareTo(cartItem.getSubtotal()));
    }

    @Test
    void testGetSubtotalWhenUnitPriceIsNull() {
        cartItem.setQuantity(2);
        cartItem.setUnitPrice(null);

        assertThrows(NullPointerException.class, cartItem::getSubtotal);
    }
}

