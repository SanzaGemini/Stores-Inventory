package com.stores.Inventory.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<OrderItem> orderItems;

    public BigDecimal getTotalPrice(){
        setToTalPrice();
        return this.totalPrice;
    }

    private void setToTalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                if (orderItem != null && orderItem.getPrice() != null) {
                    totalPrice = totalPrice.add(calculateOrderPrice(orderItem.getPrice(), orderItem.getQuantity()));
                }
            }
        }
        this.totalPrice = totalPrice.setScale(2, RoundingMode.CEILING);
    }

    private BigDecimal calculateOrderPrice(BigDecimal price,int quantity){
        if (price == null) throw new NullPointerException("Price Is Null");
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
