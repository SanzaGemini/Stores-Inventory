package com.stores.Inventory.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Long id;
    private LocalDateTime localDateTime;
    private BigDecimal totalPrice;
    private List<OrderItemDTO> orderItems;

}
