package com.stores.Inventory.service;

import com.stores.Inventory.model.*;
import com.stores.Inventory.model.dto.CartDTO;
import com.stores.Inventory.model.dto.CartItemDTO;
import com.stores.Inventory.model.dto.OrderDTO;
import com.stores.Inventory.model.dto.OrderItemDTO;
import com.stores.Inventory.repository.CartItemRepository;
import com.stores.Inventory.repository.CartRepository;
import com.stores.Inventory.repository.OrderRepository;
import com.stores.Inventory.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Adds a product to the user's cart. If no cart exists, creates a new one.
     */
    @Transactional
    public CartDTO addItemToCart(Long userId, Long productId, int quantity) {
        // Find or create cart for user
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    User user = new User();
                    user.setId(userId);
                    newCart.setUser(user);
                    return newCart;
                });

        // Find product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        // Add product to cart
        cart.addProduct(product, quantity);
        cartRepository.save(cart);

        return convertToCartDTO(cart);
    }

    /**
     * Persists the cart to the database.
     */
    @Transactional
    public CartDTO saveCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("Cart not found for user: " + userId));
        cartRepository.save(cart);
        return convertToCartDTO(cart);
    }

    /**
     * Checks out the cart, creates an order, clears the cart, and saves the order.
     */
    @Transactional
    public OrderDTO checkout(Long userId) {
        // Find cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException("Cart not found for user: " + userId));

        // Create user reference
        User user = new User();
        user.setId(userId);

        // Create order from cart
        Order order = cart.checkout(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        // Save order
        orderRepository.save(order);

        // Clear and delete cart
        cart.clear();
        cartRepository.delete(cart);

        return convertToOrderDTO(order);
    }

    /**
     * Converts Cart to CartDTO for response.
     */
    private CartDTO convertToCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCreatedAt(cart.getCreatedAt());
        cartDTO.setTotal(cart.getTotal());
        cartDTO.setItems(cart.getItems().stream().map(cartItem -> {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setId(cartItem.getId());
            itemDTO.setProductId(cartItem.getProduct().getId());
            itemDTO.setQuantity(cartItem.getQuantity());
            itemDTO.setSubtotal(cartItem.getSubtotal());
            return itemDTO;
        }).collect(Collectors.toList()));
        return cartDTO;
    }

    /**
     * Converts Order to OrderDTO for response.
     */
    private OrderDTO convertToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setLocalDateTime(order.getCreatedAt());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setOrderItems(order.getOrderItems().stream().map(orderItem -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setId(orderItem.getId());
            itemDTO.setQuantity(orderItem.getQuantity());
            itemDTO.setPrice(orderItem.getPrice());
            itemDTO.setProduct(orderItem.getProduct());
            itemDTO.setOrder(order);
            return itemDTO;
        }).collect(Collectors.toList()));
        return orderDTO;
    }
}