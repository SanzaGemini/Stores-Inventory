package com.stores.Inventory.Unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.ProductDTO;

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
        product = new Product("Laptop", "A powerful laptop", 999.99, 10);
    }

    /**
     * Test the constructor and getters.
     */
    @Test
    public void testConstructorAndGetters() {
        assertNotNull(product);
        assertEquals("Laptop", product.getName());
        assertEquals("A powerful laptop", product.getDescription());
        assertEquals(999.99, product.getPrice());
        assertEquals(10, product.getQuantity());
    }

    /**
     * Test setters.
     */
    @Test
    public void testSetters() {
        product.setId(1L);
        product.setName("Smartphone");
        product.setDescription("A modern smartphone");
        product.setPrice(499.99);
        product.setQuantity(20);

        assertEquals(1L, product.getId());
        assertEquals("Smartphone", product.getName());
        assertEquals("A modern smartphone", product.getDescription());
        assertEquals(499.99, product.getPrice());
        assertEquals(20, product.getQuantity());
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
    }

    @Test
    public void testProductUpdate(){
        Product product = new Product("Product name", "Product description", 0.0, 0);

        assertEquals("Product name", product.getName());
        assertEquals("Product description", product.getDescription());
        assertEquals(0.0, product.getPrice());
        assertEquals(0, product.getQuantity());

        ProductDTO productDTO = new ProductDTO("Updated name", "Updated description", 9.99, 9);
        product.update(productDTO);

        assertEquals("Updated name", product.getName());
        assertEquals("Updated description", product.getDescription());
        assertEquals(9.99, product.getPrice());
        assertEquals(9, product.getQuantity());
    }
}
