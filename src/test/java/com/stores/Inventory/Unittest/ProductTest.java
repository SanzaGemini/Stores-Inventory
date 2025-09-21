package com.stores.Inventory.Unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.dto.ProductDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Product class.
 */
public class ProductTest {

    private Product product;

    /**
     * Set up a new Product instance before each test.
     */
    @BeforeEach
    public void setUp() {
        product = new Product("Laptop", "A powerful laptop", BigDecimal.valueOf(999.99), 10,"Tech");
    }

    /**
     * Test the constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        assertNotNull(product);
        assertEquals("Laptop", product.getName());
        assertEquals("A powerful laptop", product.getDescription());
        assertEquals(BigDecimal.valueOf(999.99), product.getPrice());
        assertEquals(10, product.getQuantity());
        assertEquals("Tech",product.getCategory());
    }

    /**
     * Test setters.
     */
    @Test
    public void testSetters() {
        BigDecimal expectedPrice = BigDecimal.valueOf(499.99);

        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("A modern smartphone");
        product.setPrice(expectedPrice);
        product.setQuantity(20);
        product.setCategory("Tech");

        assertEquals(1L, product.getId());
        assertEquals("Smartphone", product.getName());
        assertEquals("A modern smartphone", product.getDescription());
        assertEquals(expectedPrice, product.getPrice());
        assertEquals(20, product.getQuantity());
        assertEquals("Tech",product.getCategory());
    }

    /**
     * Test the toString method to ensure it returns the expected string format.
     */
    @Test
    public void testToString() {
        String expectedString = "Laptop - A powerful laptop - 999.99";
        assertEquals(expectedString, product.toString());
    }

    /**
     * Test default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        Product defaultProduct = new Product();
        assertNotNull(defaultProduct);
        assertNull(defaultProduct.getName());
        assertNull(defaultProduct.getDescription());
        assertNull(defaultProduct.getPrice());
        assertNull(defaultProduct.getQuantity());
        assertNull(defaultProduct.getCategory());
    }

    @Test
    public void testProductUpdate(){
        Product product = new Product("Product name", "Product description", BigDecimal.valueOf(0.0), 0,"A Product");
        BigDecimal expectedPrice = BigDecimal.valueOf(9.99);

        assertEquals("Product name", product.getName());
        assertEquals("Product description", product.getDescription());
        assertEquals(BigDecimal.valueOf(0.0), product.getPrice());
        assertEquals(0, product.getQuantity());
        assertEquals("A Product",product.getCategory());

        ProductDTO productDTO = new ProductDTO("Updated name", "Updated description", expectedPrice, 9,"Updated Product");
        product.update(productDTO);

        assertEquals("Updated name", product.getName());
        assertEquals("Updated description", product.getDescription());
        assertEquals(expectedPrice, product.getPrice());
        assertEquals(9, product.getQuantity());
        assertEquals("Updated Product",product.getCategory());
    }
}
