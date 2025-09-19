package com.stores.Inventory.Unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


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
        product = new Product("car", "BMW",  10000.0, 2);
        productList = new ArrayList<>();
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
    public void GetProductById(){
        // mock when the get product by id method In the productRepository.findById() is called
        when(productRepository.findById(0L)).thenReturn(Optional.of(product));

        // Call the getProductById(Long is) in The Product Service
        Product product1 = productService.getProductByID(0L);

        // Verify that the repository's  findByID(Long id) was called
        verify(productRepository,times(1)).findById(0L);

        // Verify that the product was returned
        assertEquals(product,product1);
    }

    @Test
    public void testSave() {
        // Stub the save method to return the product when productRepository.save() is called
        when(productRepository.save(product)).thenReturn(product);
        
        // Call the save method in ProductService and pass the mocked productDTO
        Product products = productService.save(productDTO);

        // Verify that the repository's Save method was called
        verify(productRepository, times(1)).save(product);
        
        // Verify that the product list is not null
        assertNotNull(products);
    }

    @Test
    public void deleteProduct(){
        
        when(productRepository.findById(0L)).thenReturn(Optional.of(product));

        Boolean message = productService.delete(0L);
        
        verify(productRepository, times(1)).delete(product);

        assertTrue(message);
    }

    @Test
    public void deleteProductThatDoesNotExist(){
        
        when(productRepository.findById(0L)).thenReturn(Optional.empty());

        Boolean message = productService.delete(0L);
        
        verify(productRepository, times(0)).delete(product);

        assertFalse(message );
    }

    @Test
    public void updateProduct(){
        productDTO = new ProductDTO("BMW","M4 CS",3999999.90,1);

        when(productRepository.findById(0L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(productDTO.toProduct());

        product = productService.update(0L, productDTO);

        assertEquals("BMW", product.getName());
        assertEquals("M4 CS", product.getDescription());
        assertEquals(3999999.90, product.getPrice());
        assertEquals(1, product.getQuantity());

    }

    

}
