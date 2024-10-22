package com.stores.Inventory.AcceptanceTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stores.Inventory.InventoryApplication;
import com.stores.Inventory.model.Product;
import com.stores.Inventory.repository.ProductRepository;

@SpringBootTest
@ContextConfiguration(classes = InventoryApplication.class)
@AutoConfigureMockMvc
class InventoryApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Product> pList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice((float)100.0);
        product.setQuantity(10);

        productRepository.save(product);
        pList.add(product);
    }

    @AfterEach
    public void tearDown() {
        for (Product product : pList) {
            productRepository.delete(product);
        }
        pList.clear();
    }

    @Test
    void findAllProductsIntegrationTest() throws Exception {
        // Convert the list of products to JSON
        String expectedJson = objectMapper.writeValueAsString(pList);

        mockMvc.perform(get("/products"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson));  // Check if response matches the expected JSON
    }
}
