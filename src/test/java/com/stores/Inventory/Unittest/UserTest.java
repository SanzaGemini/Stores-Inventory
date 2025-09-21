package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.Cart;
import com.stores.Inventory.model.Order;
import com.stores.Inventory.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultCreatedAtIsSet() {
        assertNotNull(user.getCreatedAt(), "createdAt should be initialized");
        assertTrue(user.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(1)),
                "createdAt should be close to now");
    }

    @Test
    void testSetAndGetId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void testSetAndGetName() {
        user.setName("Alice");
        assertEquals("Alice", user.getName());
    }

    @Test
    void testSetAndGetEmail() {
        user.setEmail("alice@example.com");
        assertEquals("alice@example.com", user.getEmail());
    }

    @Test
    void testSetAndGetPasswordHash() {
        user.setPasswordHash("hashed_password");
        assertEquals("hashed_password", user.getPasswordHash());
    }

    @Test
    void testOrdersRelationship() {
        Order order1 = new Order();
        Order order2 = new Order();

        var orders = new ArrayList<Order>();
        orders.add(order1);
        orders.add(order2);

        user.setOrders(orders);

        assertEquals(2, user.getOrders().size());
        assertTrue(user.getOrders().contains(order1));
        assertTrue(user.getOrders().contains(order2));
    }

    @Test
    void testCartRelationship() {
        Cart cart = new Cart();
        user.setCart(cart);

        assertEquals(cart, user.getCart());
    }
}

