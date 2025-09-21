package com.stores.Inventory.model;

import com.stores.Inventory.model.dto.ProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a product entity.
 */
@Setter
@Getter
@Entity
@Table(name = "products")
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
    private BigDecimal price;

    @Column(name = "Quantity")
    @NotNull(message = "Quantity is required!")
    private Integer quantity;

    @Column(name = "Category")
    @NotNull(message = "Category is Required")
    private String category;

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
    public Product(String name, String description, BigDecimal price, Integer quantity,String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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

    public void update(ProductDTO productDTO){
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
        this.quantity = productDTO.getQuantity();
        this.category = productDTO.getCategory();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return id == product.id && Objects.equals(name, product.name)
                && Objects.equals(description, product.description)
                && Objects.equals(price, product.price)
                && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, quantity);
    }
}
