package com.stores.Inventory.model;
/**
 * A Data Transfer Object (DTO) class for transferring product data.
 * This class provides getter and setter methods for product attributes
 * and a method to convert the DTO to a `Product` entity.
 */
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
        return new Product(name, description, price, quantity);
    }

    /**
     * Gets the name of the product.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * 
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the product.
     * 
     * @return the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     * 
     * @param description the description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the product.
     * 
     * @return the product price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * 
     * @param price the price of the product
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the product.
     * 
     * @return the product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     * 
     * @param quantity the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
