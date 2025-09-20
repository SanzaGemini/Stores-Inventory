package com.stores.Inventory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime DateTime;
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
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public void setLocalDateTime(LocalDateTime now) {
        this.DateTime = now;
    }

    public LocalDateTime getLocalDateTime() {
        return this.DateTime;
    }
}
