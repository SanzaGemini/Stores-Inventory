package com.stores.Inventory.model;

import com.stores.Inventory.model.dto.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @NotBlank(message = "Name is required!")
    @Size(min = 2, message = "Name should be more than 2 characters long!")
    @Column(nullable = false, length = 200)
    private String name;

    @NotBlank(message = "Description is required!")
    @Size(min = 5, message = "Description should be more than 5 characters long!")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @NotNull(message = "Price is required!")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull(message = "Quantity is required!")
    @Column(name = "stock_quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Category is required!")
    @Column(length = 100, nullable = false)
    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // --- Constructors ---
    public Product() {}

    public Product(String name, String description, BigDecimal price, Integer quantity, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // --- Update method from DTO ---
    public void update(ProductDTO productDTO){
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
        this.quantity = productDTO.getQuantity();
        this.category = productDTO.getCategory();
    }

    @Override
    public String toString() {
        return name + " - " + description + " - " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return id == product.id
                && Objects.equals(name, product.name)
                && Objects.equals(description, product.description)
                && Objects.equals(price, product.price)
                && Objects.equals(quantity, product.quantity)
                && Objects.equals(category,product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, quantity);
    }
}
