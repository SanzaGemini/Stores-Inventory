package com.stores.Inventory.model;

import lombok.Getter;
import lombok.Setter;

/**
 * A Data Transfer Object (DTO) class for transferring product data.
 * This class provides getter and setter methods for product attributes
 * and a method to convert the DTO to a `Product` entity.
 */
@Getter
@Setter
public class ProductDTO {

    private String name;
    private String description;
    private Double price;
    private int quantity;

    /**
     * Converts this ProductDTO into a Product entity.
     * 
     * @return a new Product object containing the data from this DTO
     */
    public Product toProduct() {
        return  new Product().setName(name)
        .setDescription(description)
        .setPrice(price)
        .setQuantity(quantity);
    }
}
