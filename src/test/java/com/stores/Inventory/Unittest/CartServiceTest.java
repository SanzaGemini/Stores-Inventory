package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.*;
import com.stores.Inventory.model.dto.CartDTO;
import com.stores.Inventory.model.dto.OrderDTO;
import com.stores.Inventory.repository.CartRepository;
import com.stores.Inventory.repository.OrderRepository;
import com.stores.Inventory.repository.ProductRepository;
import com.stores.Inventory.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private CartService cartService;

    private Product product;
    private Cart cart;
    private Order order;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(new BigDecimal("10.00"));
        product.setQuantity(100);
        product.setCategory("Test Category");

        cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setCreatedAt(LocalDateTime.now());

        order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
    }

    @Test
    void addItemToCart_NewCart_Success() {
        // Arrange
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> {
            Cart savedCart = invocation.getArgument(0);
            savedCart.setId(1L);
            return savedCart;
        });

        // Act
        CartDTO result = cartService.addItemToCart(1L, 1L, 2);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(1L, result.getItems().get(0).getProductId());
        assertEquals(2, result.getItems().get(0).getQuantity());
        assertEquals(new BigDecimal("20.00"), result.getItems().get(0).getSubtotal());
        assertEquals(new BigDecimal("20.00"), result.getTotal());
        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    void addItemToCart_ExistingCart_AddsItem() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setUnitPrice(product.getPrice());
        cart.setItems(new ArrayList<>(List.of(cartItem)));

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // Act
        CartDTO result = cartService.addItemToCart(1L, 1L, 1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals(2, result.getItems().get(0).getQuantity()); // Quantity incremented
        assertEquals(new BigDecimal("20.00"), result.getItems().get(0).getSubtotal());
        assertEquals(new BigDecimal("20.00"), result.getTotal());
        verify(cartRepository).save(cart);
    }

    @Test
    void addItemToCart_ProductNotFound_ThrowsException() {
        // Arrange
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> cartService.addItemToCart(1L, 1L, 1));
        assertEquals("Product not found: 1", exception.getMessage());
        verify(cartRepository, never()).save(any(Cart.class));
    }

    @Test
    void saveCart_CartExists_Success() {
        // Arrange
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(cartRepository.save(cart)).thenReturn(cart);

        // Act
        CartDTO result = cartService.saveCart(1L);

        // Assert
        assertNotNull(result);
        assertEquals(cart.getCreatedAt(), result.getCreatedAt());
        assertEquals(cart.getTotal(), result.getTotal());
        verify(cartRepository).save(cart);
    }

    @Test
    void saveCart_CartNotFound_ThrowsException() {
        // Arrange
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> cartService.saveCart(1L));
        assertEquals("Cart not found for user: 1", exception.getMessage());
        verify(cartRepository, never()).save(any(Cart.class));
    }

    @Test
    void checkout_CartExists_CreatesOrderAndClearsCart() {
        // Arrange
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);
        cartItem.setUnitPrice(product.getPrice());
        cart.setItems(new ArrayList<>(List.of(cartItem)));

        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(1L);
            return savedOrder;
        });

        // Act
        OrderDTO result = cartService.checkout(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1, result.getOrderItems().size());
        assertEquals(2, result.getOrderItems().get(0).getQuantity());
        assertEquals(new BigDecimal("10.00"), result.getOrderItems().get(0).getPrice());
        assertEquals(new BigDecimal("20.00"), result.getTotalPrice());
        assertEquals(OrderStatus.PENDING, order.getStatus());
        verify(orderRepository).save(any(Order.class));
        verify(cartRepository).delete(cart);
        assertTrue(cart.getItems().isEmpty()); // Cart should be cleared
    }

    @Test
    void checkout_CartNotFound_ThrowsException() {
        // Arrange
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> cartService.checkout(1L));
        assertEquals("Cart not found for user: 1", exception.getMessage());
        verify(orderRepository, never()).save(any(Order.class));
        verify(cartRepository, never()).delete(any(Cart.class));
    }

    @Test
    void checkout_EmptyCart_ThrowsException() {
        // Arrange
        when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> cartService.checkout(1L));
        assertEquals("Cart is empty; cannot checkout", exception.getMessage());
        verify(orderRepository, never()).save(any(Order.class));
        verify(cartRepository, never()).delete(any(Cart.class));
    }
}