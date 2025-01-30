package com.stores.Inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a product entity.
 */
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name")
    @NotBlank(message = "Name is required!")
    @Size(min = 2, message = "Name should be more than 2 characters long!")
    private String name;

    @Column(name = "Description")
    @NotBlank(message = "Description is required!")
    @Size(min = 5, message = "Description should be more than 5 characters long!")
    private String description;

    @Column(name = "Price")
    @NotNull(message = "Price is required!")
    private Double price;

    @Column(name = "Quantity")
    @NotNull(message = "Quantity is required!")
    private Integer quantity;

    /**
     * Default constructor for Product.
     */
    public Product() {
    }

    /**
     * Parameterized constructor for Product.
     *
     * @param name        the name of the product
     * @param description the description of the product
     * @param price       the price of the product
     * @param quantity    the quantity of the product
     */
    public Product(String name, String description, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Get the product ID.
     *
     * @return the product ID
     */
    public long getId() {
        return id;
    }

    /**
     * Set the product ID.
     *
     * @param id the product ID
     */
    public Product setId(long id) {
        this.id = id;
        return this;
    }

    /**
     * Get the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the product name.
     *
     * @param name the product name
     */
    public Product setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the product description.
     *
     * @return the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the product description.
     *
     * @param description the product description
     */
    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the product price.
     *
     * @return the product price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set the product price.
     *
     * @param price the product price
     */
    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    /**
     * Get the product quantity.
     *
     * @return the product quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Set the product quantity.
     *
     * @param quantity the product quantity
     */
    public Product setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Returns a string representation of the product in the format:
     * Name - Description - Price
     * 
     * @return a formatted string with the product details
     */
    @Override
    public String toString() {
        return name + " - " + description + " - " + price;
    }
}
