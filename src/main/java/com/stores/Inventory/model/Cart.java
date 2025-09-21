package com.stores.Inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {

    private List<CartItem> items = new ArrayList<>();
    private LocalDateTime createdAt = LocalDateTime.now();

    // Add product to cart (or increment quantity if exists)
    public List<CartItem> addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (product == null || product.getPrice() == null) {
            throw new IllegalArgumentException("Product or price cannot be null");
        }

        Optional<CartItem> existingItem = items.stream()
                .filter(item -> Objects.equals(item.getProductId(),product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProductId(product.getId());
            newItem.setQuantity(quantity);
            newItem.setUnitPrice(product.getPrice()); // Snapshot
            items.add(newItem);
        }
        return this.items;
    }

    // Remove product from cart
    public void removeProduct(Product product) {
        items.removeIf(item -> Objects.equals(item.getProductId(),product.getId()));
    }

    // Update quantity
    public void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            removeProduct(product);
            return;
        }
        items.stream()
                .filter(item -> Objects.equals(item.getProductId(),product.getId()))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
    }

    // Calculate total (sum of subtotals, rounded to 2 decimals)
    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }

    // Checkout: Create Order and OrderItems from cart
    public Order checkout() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty; cannot checkout");
        }

        Order order = new Order();
        order.setLocalDateTime(LocalDateTime.now());
        order.setOrderItems(new ArrayList<>());

        for (CartItem cartItem : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getUnitPrice()); // Use snapshot price
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setOrderId(order.getId());
            order.getOrderItems().add(orderItem);
        }

        order.setTotalPrice(order.getTotalPrice()); // Triggers calculation
        return order;
    }

    public void clear() {
        items.clear();
    }
}