package com.stores.Inventory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;
}
