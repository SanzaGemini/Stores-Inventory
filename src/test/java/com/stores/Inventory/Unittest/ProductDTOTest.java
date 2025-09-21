package com.stores.Inventory.Unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.dto.ProductDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ProductDTO class.
 */
public class ProductDTOTest {

    private ProductDTO productDTO;

    /**
     * Set up a new ProductDTO instance before each test.
     */
    @BeforeEach
    public void setUp() {
        productDTO = new ProductDTO();
        productDTO.setName("Laptop");
        productDTO.setDescription("A powerful laptop");
        productDTO.setPrice(BigDecimal.valueOf(999.99));
        productDTO.setQuantity(10);
        productDTO.setCategory("Tech");
    }

    /**
     * Test getters and setters.
     */
    @Test
    public void testGettersAndSetters() {
        assertEquals("Laptop", productDTO.getName());
        assertEquals("A powerful laptop", productDTO.getDescription());
        assertEquals(BigDecimal.valueOf(999.99), productDTO.getPrice());
        assertEquals(10, productDTO.getQuantity());
        assertEquals("Tech",productDTO.getCategory());
    }

    /**
     * Test the toProduct method.
     */
    @Test
    public void testToProduct() {
        Product product = productDTO.toProduct();

        assertNotNull(product);
        assertEquals("Laptop", product.getName());
        assertEquals("A powerful laptop", product.getDescription());
        assertEquals(BigDecimal.valueOf(999.99), product.getPrice());
        assertEquals(10, product.getQuantity());
        assertEquals("Tech",product.getCategory());
    }

    /**
     * Test default constructor.
     */
    @Test
    public void testDefaultConstructor() {
        ProductDTO defaultProductDTO = new ProductDTO();
        assertNull(defaultProductDTO.getName());
        assertNull(defaultProductDTO.getDescription());
        assertNull(defaultProductDTO.getPrice());
        assertEquals(0, defaultProductDTO.getQuantity());
    }
}
