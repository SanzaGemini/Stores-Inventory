package com.stores.Inventory.Unittest;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.dto.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Laptop", "Gaming Laptop", new BigDecimal("1999.99"), 10, "Electronics");
        product.setId(1L);
    }

    @Test
    void testConstructorSetsFieldsCorrectly() {
        assertEquals("Laptop", product.getName());
        assertEquals("Gaming Laptop", product.getDescription());
        assertEquals(new BigDecimal("1999.99"), product.getPrice());
        assertEquals(10, product.getQuantity());
        assertEquals("Electronics", product.getCategory());
    }

    @Test
    void testCreatedAtIsInitialized() {
        Product newProduct = new Product();
        assertNotNull(newProduct.getCreatedAt());
        assertTrue(newProduct.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testSetAndGetId() {
        product.setId(5L);
        assertEquals(5L, product.getId());
    }

    @Test
    void testUpdateFromProductDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setName("Smartphone");
        dto.setDescription("Latest model smartphone");
        dto.setPrice(new BigDecimal("899.99"));
        dto.setQuantity(50);
        dto.setCategory("Mobiles");

        product.update(dto);

        assertEquals("Smartphone", product.getName());
        assertEquals("Latest model smartphone", product.getDescription());
        assertEquals(new BigDecimal("899.99"), product.getPrice());
        assertEquals(50, product.getQuantity());
        assertEquals("Mobiles", product.getCategory());
    }

    @Test
    void testToStringFormat() {
        String expected = "Laptop - Gaming Laptop - 1999.99";
        assertEquals(expected, product.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Product sameProduct = new Product("Laptop", "Gaming Laptop", new BigDecimal("1999.99"), 10, "Electronics");
        sameProduct.setId(1L);

        Product differentProduct = new Product("Tablet", "Android Tablet", new BigDecimal("299.99"), 5, "Electronics");
        differentProduct.setId(2L);

        assertEquals(product, sameProduct);
        assertEquals(product.hashCode(), sameProduct.hashCode());
        assertNotEquals(product, differentProduct);
    }

    @Test
    void testEqualsWithDifferentType() {
        assertNotEquals("Some String".getClass(), product.getClass());
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, product);
    }
}
