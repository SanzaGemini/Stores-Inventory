package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.Order;
import com.stores.Inventory.model.OrderItem;
import com.stores.Inventory.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        orderItem = new OrderItem();
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(orderItem);
        assertNull(orderItem.getId());
        assertEquals(0, orderItem.getQuantity());
        assertNull(orderItem.getPrice());
        assertNull(orderItem.getProduct());
        assertNull(orderItem.getOrder());
    }

    @Test
    void testAllArgsConstructor() {
        Product product = new Product();
        Order order = new Order();
        BigDecimal price = new BigDecimal("150.00");

        OrderItem item = new OrderItem(2, price, product, order);

        assertEquals(2, item.getQuantity());
        assertEquals(price, item.getPrice());
        assertEquals(product, item.getProduct());
        assertEquals(order, item.getOrder());
    }

    @Test
    void testSetAndGetId() {
        orderItem.setId(10L);
        assertEquals(10L, orderItem.getId());
    }

    @Test
    void testSetAndGetQuantity() {
        orderItem.setQuantity(5);
        assertEquals(5, orderItem.getQuantity());
    }

    @Test
    void testSetAndGetPrice() {
        BigDecimal price = new BigDecimal("99.99");
        orderItem.setPrice(price);
        assertEquals(price, orderItem.getPrice());
    }

    @Test
    void testSetAndGetProduct() {
        Product product = new Product();
        orderItem.setProduct(product);
        assertEquals(product, orderItem.getProduct());
    }

    @Test
    void testSetAndGetOrder() {
        Order order = new Order();
        orderItem.setOrder(order);
        assertEquals(order, orderItem.getOrder());
    }
}
