package com.stores.Inventory.model.dto;

import com.stores.Inventory.model.Order;
import com.stores.Inventory.model.OrderItem;
import com.stores.Inventory.model.Product;
import com.stores.Inventory.repository.OrderRepository;
import com.stores.Inventory.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    private Long id;
    private int quantity;
    private BigDecimal price;
    private Product product;
    private Order order;

    public OrderItem toOrderItem(){
        return new OrderItem(quantity,price, product,order);
    }
}