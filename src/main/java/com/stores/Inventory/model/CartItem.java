package com.stores.Inventory.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItem {
    private Long productId;
    private int quantity;
    private BigDecimal unitPrice; // Snapshot of price at add time to avoid changes

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}