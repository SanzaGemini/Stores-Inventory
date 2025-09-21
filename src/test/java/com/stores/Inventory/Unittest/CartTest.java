package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private Product product1;
    private Product product2;
    private User user;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        user = new User();

        product1 = new Product("Laptop", "Gaming laptop", new BigDecimal("1200.00"), 5, "Electronics");
        product2 = new Product("Mouse", "Wireless mouse", new BigDecimal("25.50"), 20, "Electronics");
    }

    @Test
    void testAddProduct_NewItem() {
        cart.addProduct(product1, 2);

        assertEquals(1, cart.getItems().size());
        CartItem item = cart.getItems().get(0);
        assertEquals(product1, item.getProduct());
        assertEquals(2, item.getQuantity());
        assertEquals(new BigDecimal("1200.00"), item.getUnitPrice());
        assertEquals(cart, item.getCart()); // back-reference
    }

    @Test
    void testAddProduct_IncrementExistingItem() {
        cart.addProduct(product1, 2);
        cart.addProduct(product1, 3);

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).getQuantity());
    }

    @Test
    void testAddProduct_InvalidQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(product1, 0));
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(product1, -5));
    }

    @Test
    void testAddProduct_NullProductThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(null, 1));
    }

    @Test
    void testAddProduct_NullPriceThrowsException() {
        Product invalidProduct = new Product();
        invalidProduct.setName("Faulty");
        invalidProduct.setDescription("Missing price");
        invalidProduct.setQuantity(1);
        invalidProduct.setCategory("Misc");
        invalidProduct.setPrice(null);

        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(invalidProduct, 1));
    }

    @Test
    void testRemoveProduct_RemovesCorrectly() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 1);

        cart.removeProduct(product1);

        assertEquals(1, cart.getItems().size());
        assertEquals(product2, cart.getItems().get(0).getProduct());
    }

    @Test
    void testUpdateQuantity_IncreasesAndDecreasesCorrectly() {
        cart.addProduct(product1, 2);
        cart.updateQuantity(product1, 5);

        assertEquals(5, cart.getItems().get(0).getQuantity());

        cart.updateQuantity(product1, 0); // should remove product
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    void testGetTotal_CalculatesCorrectly() {
        cart.addProduct(product1, 1); // 1200.00
        cart.addProduct(product2, 2); // 25.50 * 2 = 51.00

        assertEquals(new BigDecimal("1251.00"), cart.getTotal());
    }

    @Test
    void testCheckout_ConvertsCartToOrder() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 2);

        Order order = cart.checkout(user);

        assertNotNull(order);
        assertEquals(user, order.getUser());
        assertEquals(2, order.getOrderItems().size());
        assertEquals(new BigDecimal("1251.00"), order.getTotalPrice());

        // Each OrderItem should reference the same products and snapshot price
        List<OrderItem> items = order.getOrderItems();
        assertEquals(product1, items.get(0).getProduct());
        assertEquals(new BigDecimal("1200.00"), items.get(0).getPrice());

        assertEquals(product2, items.get(1).getProduct());
        assertEquals(new BigDecimal("25.50"), items.get(1).getPrice());
    }

    @Test
    void testCheckout_EmptyCartThrowsException() {
        assertThrows(IllegalStateException.class, () -> cart.checkout(user));
    }

    @Test
    void testClear_EmptiesCart() {
        cart.addProduct(product1, 1);
        cart.addProduct(product2, 1);

        cart.clear();
        assertTrue(cart.getItems().isEmpty());
    }
}
