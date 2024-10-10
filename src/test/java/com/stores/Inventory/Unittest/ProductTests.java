package com.stores.Inventory.Unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stores.Inventory.model.Product;

@SpringBootTest
public class ProductTests {
    public Product product;


    @BeforeEach
    public void setUp(){
        product  = new Product("BMW","M5 CS",(float) 2690000.0,2);
    }

    @AfterEach
    public void tearDowm(){
        product = null;
    }

    @Test
    public void createProduct(){
        assertEquals(product.getClass(), Product.class);
    }

    @Test
    public void getAttributes(){
        assertEquals("BMW", product.getName());
        assertEquals("M5 CS", product.getDescription());
        assertEquals((float) 2690000.0, product.getPrice());
        assertEquals(2, product.getQuantity());
    }
    
}
