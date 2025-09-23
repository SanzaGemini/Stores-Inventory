// CartItemDTO.java
package com.stores.Inventory.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal subtotal;
}