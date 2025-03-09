package com.stores.Inventory.Unittest;

import com.stores.Inventory.controller.ProductController;
import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.ProductDTO;
import com.stores.Inventory.repository.ProductRepository;
import com.stores.Inventory.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Given
        Product product1 = new Product("Product 1", "Description 1", 19.99, 100);
        Product product2 = new Product("Product 2", "Description 2", 29.99, 50);
        List<Product> products = Arrays.asList(product1, product2);

        // When
        when(productService.findAllProducts()).thenReturn(products);

        // Then
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Request was successful"))
                .andExpect(jsonPath("$.data[0].name").value("Product 1"))  // Ensure null or empty name
                .andExpect(jsonPath("$.data[0].description").value("Description 1"))  // Ensure null or empty description
                .andExpect(jsonPath("$.data[0].price").value(19.99));

        verify(productService, times(1)).findAllProducts();
    }

    
    @Test
    public void testAddProduct2() throws Exception {
        // Given
        Product product = new ProductDTO("Product 3", "Description 3", 39.99, 200).toProduct();

        // When
        when(productService.save(any(ProductDTO.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Product 3\", \"description\":\"Description 3\", \"price\":39.99, \"quantity\":200}")
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.status").value("success"))
                    .andExpect(jsonPath("$.message").value("Request was successful"))
                    .andExpect(jsonPath("$.data.name").value("Product 3"))
                    .andExpect(jsonPath("$.data.description").value("Description 3"))
                    .andExpect(jsonPath("$.data.price").value(39.99))
                    .andExpect(jsonPath("$.data.quantity").value(200))
                    .andExpect(jsonPath("$.data.id").value(0))  // Assuming id is 0 here
                    .andReturn();

        // Verify the mock service method was called once
        verify(productService, times(1)).save(any(ProductDTO.class));
    }

    @Test
    public void testDeleteProduct() throws Exception{

        when(productService.delete(0L)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/{id}", 0L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value("success"))
            .andExpect(jsonPath("$.message").value("Request was successful"))
            .andExpect(jsonPath("$.data").value("The product was successfully Deleted."));

            verify(productService, times(1)).delete(any(Long.class));
    }

    @Test
    public void testDeleteProductThatDoesNotExist() throws Exception{

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/v1/{id}", 1L))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value("failed"))
            .andExpect(jsonPath("$.message").value("Can Not Delete None Existing Product."));

            verify(productService, times(1)).delete(any(Long.class));
    }


    @Test
    public void testUpdateProduct() throws Exception {
        // Given
        Product product = new ProductDTO("Product 4", "Description 4", 49.99, 250).toProduct();

        // When
        when(productService.save(any(ProductDTO.class))).thenReturn(product);

        mockMvc.perform(post("/api/v1/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Product 3\", \"description\":\"Description 3\", \"price\":39.99, \"quantity\":200}")
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.status").value("success"))
                    .andExpect(jsonPath("$.message").value("Request was successful"))
                    .andExpect(jsonPath("$.data.name").value("Product 3"))
                    .andExpect(jsonPath("$.data.description").value("Description 3"))
                    .andExpect(jsonPath("$.data.price").value(39.99))
                    .andExpect(jsonPath("$.data.quantity").value(200))
                    .andExpect(jsonPath("$.data.id").value(0))  // Assuming id is 0 here
                    .andReturn();

        // Verify the mock service method was called once
        verify(productService, times(1)).save(any(ProductDTO.class));
    }
}
