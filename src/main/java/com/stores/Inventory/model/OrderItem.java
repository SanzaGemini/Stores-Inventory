package com.stores.Inventory.model;

import com.stores.Inventory.repository.OrderRepository;
import com.stores.Inventory.repository.ProductRepository;
import com.stores.Inventory.service.OrderService;
import com.stores.Inventory.service.ProductService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private BigDecimal price;
    // Relationship to Product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Relationship to Order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderItem(){

    }

    public OrderItem(int quantity, BigDecimal price, Product product, Order order) {
            this.quantity = quantity;
            this.price = price;
            this.product = product;
            this.order = order;
        }

}
