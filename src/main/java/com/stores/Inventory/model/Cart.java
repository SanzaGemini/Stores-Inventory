package com.stores.Inventory.model;

import jakarta.persistence.*;
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
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    // Link to user (one user = one active cart)
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Items in this cart
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // --- Business logic ---

    // Add product to cart (or increment quantity)
    public List<CartItem> addProduct(Product product, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (product == null || product.getPrice() == null) throw new IllegalArgumentException("Product or price cannot be null");

        Optional<CartItem> existingItem = items.stream()
                .filter(item -> Objects.equals(item.getProduct(), product))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(this); // 🔑 set back-reference
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setUnitPrice(product.getPrice()); // snapshot
            items.add(newItem);
        }
        return this.items;
    }

    // Remove product
    public void removeProduct(Product product) {
        items.removeIf(item -> Objects.equals(item.getProduct(), product));
    }

    // Update quantity
    public void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            removeProduct(product);
            return;
        }
        items.stream()
                .filter(item -> Objects.equals(item.getProduct(), product))
                .findFirst()
                .ifPresent(item -> item.setQuantity(quantity));
    }

    // Calculate total
    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    // Checkout (convert cart → order)
    public Order checkout(User user) {
        if (items.isEmpty()) throw new IllegalStateException("Cart is empty; cannot checkout");

        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(new ArrayList<>());

        for (CartItem cartItem : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getUnitPrice()); // snapshot
            order.getOrderItems().add(orderItem);
        }

        order.getTotalPrice(); // triggers calculation
        return order;
    }

    // Clear cart after checkout
    public void clear() {
        items.clear();
    }
}