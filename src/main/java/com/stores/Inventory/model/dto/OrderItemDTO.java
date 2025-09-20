package com.stores.Inventory.model.dto;

import com.stores.Inventory.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {

    private Long id;
    private int quantity;
    private BigDecimal price;
    private Long productId;
    private Long orderId;

    public OrderItem toOrderItem(){
        return new OrderItem(quantity,price,productId,orderId);
    }


}