// CartDTO.java
package com.stores.Inventory.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CartDTO {
    private LocalDateTime createdAt;
    private BigDecimal total;
    private List<CartItemDTO> items;
}