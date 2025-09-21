package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.Order;
import com.stores.Inventory.model.OrderItem;
import com.stores.Inventory.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class OrderItemTest {

    private OrderItem orderItem;

    @Mock
    private Product product;

    @Mock
    private Order order;

    @BeforeEach
    void setUp() {
        orderItem = new OrderItem();
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        orderItem.setId(id);
        assertEquals(id, orderItem.getId(), "ID should be set and retrieved correctly");
    }

    @Test
    void testSetAndGetQuantity() {
        int quantity = 5;
        orderItem.setQuantity(quantity);
        assertEquals(quantity, orderItem.getQuantity(), "Quantity should be set and retrieved correctly");
    }

    @Test
    void testSetAndGetPrice() {
        BigDecimal price = new BigDecimal("19.99");
        orderItem.setPrice(price);
        assertEquals(price, orderItem.getPrice(), "Price should be set and retrieved correctly");
    }

    @Test
    void testSetAndGetProduct() {
        orderItem.setProductId(product.getId());
        assertEquals(product.getId(), orderItem.getProductId(), "Product should be set and retrieved correctly");
    }

    @Test
    void testSetAndGetOrder() {
        orderItem.setOrderId(order.getId());
        assertEquals(order.getId(), orderItem.getOrderId(), "Order should be set and retrieved correctly");
    }

    @Test
    void testNullPrice() {
        orderItem.setPrice(null);
        assertNull(orderItem.getPrice(), "Price should be null when set to null");
    }

    @Test
    void testNullProduct() {
        orderItem.setProductId(null);
        assertNull(orderItem.getProductId(), "Product should be null when set to null");
    }

    @Test
    void testNullOrder() {
        orderItem.setOrderId(null);
        assertNull(orderItem.getOrderId(), "Order should be null when set to null");
    }

    @Test
    void testSetNegativeQuantity() {
        int negativeQuantity = -1;
        orderItem.setQuantity(negativeQuantity);
        assertEquals(negativeQuantity, orderItem.getQuantity(), "Negative quantity should be allowed unless validated");
    }
}
