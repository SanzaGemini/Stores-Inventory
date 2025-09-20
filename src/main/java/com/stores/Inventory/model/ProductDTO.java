package com.stores.Inventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Data Transfer Object (DTO) class for transferring product data.
 * This class provides getter and setter methods for product attributes
 * and a method to convert the DTO to a `Product` entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

    /**
     * Converts this ProductDTO into a Product entity.
     * 
     * @return a new Product object containing the data from this DTO
     */
    public Product toProduct() {
        return new Product(name, description, price, quantity);
    }
}