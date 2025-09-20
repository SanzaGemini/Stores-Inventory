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
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;

    public OrderItem(){

    }

    public OrderItem(Integer quantity,BigDecimal price,Product product,Order order){
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.order = order;
    }

}
