package com.stores.Inventory.Unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.ProductDTO;
import com.stores.Inventory.repository.ProductRepository;
import com.stores.Inventory.service.ProductService;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductDTO productDTO;

    private Product product;

    private List<Product> productList;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        
        // Create a Product instance to be returned by productDTO.toProduct()
        product = new Product("car", "BMW", (Double) 10000.0, 2);
        productList = new ArrayList<Product>();
        productList.add(product);
        
        // Mock the behavior of productDTO.toProduct() to return the Product object
        when(productDTO.toProduct()).thenReturn(product);
        
    }

    @Test
    void GetAllProducts(){
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> pList = productService.findAllProducts();

        verify(productRepository, times(1)).findAll();

        assertNotNull(pList);
    }

    @Test
    public void testSave() {
        // Stub the save method to return the product when productRepository.save() is called
        when(productRepository.save(product)).thenReturn(product);
        
        // Call the save method in ProductService and pass the mocked productDTO
        Product products = productService.save(productDTO);

        // Verify that the repository's save method was called
        verify(productRepository, times(1)).save(product);
        
        // Verify that the product list is not null
        assertNotNull(products);
    }

    @Test
    public void deleteProduct(){
        
        when(productRepository.findById(0L)).thenReturn(Optional.of(product));

        String message = productService.delete(0L);
        
        verify(productRepository, times(1)).delete(product);

        assertEquals(message, "The product was successfully Deleted.");
    }

    @Test
    public void deleteProductThatDoesNotExist(){
        
        when(productRepository.findById(0L)).thenReturn(Optional.empty());

        String message = productService.delete(0L);
        
        verify(productRepository, times(0)).delete(product);

        assertEquals("Can Not Delete None Existing Product.",message );
    }

}
