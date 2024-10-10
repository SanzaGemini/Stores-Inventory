package com.stores.Inventory.Unittest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.ProductDTO;
import com.stores.Inventory.repository.ProductRepository;
import com.stores.Inventory.service.ProductService;

public class ProductServiceTests {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDTO productDTO;

    private Product product;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        
        // Create a Product instance to be returned by productDTO.toProduct()
        product = new Product("car", "BMW", (float) 10000.0, 2);
        
        // Mock the behavior of productDTO.toProduct() to return the Product object
        when(productDTO.toProduct()).thenReturn(product);
    }

    @Test
    public void testSave() {
        // Stub the save method to return the product when productRepository.save() is called
        when(productRepository.save(product)).thenReturn(product);
        
        // Call the save method in ProductService and pass the mocked productDTO
        List<Product> products = productService.save(productDTO);

        // Verify that the repository's save method was called
        verify(productRepository, times(1)).save(product);
        
        // Verify that the product list is not null
        assertNotNull(products);
    }
}
