package com.stores.Inventory.Unittest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class InventoryApplicationTest {

    @Test
    public void contextLoads() {
        // The contextLoads() method will automatically run the application context.
        // If there are no configuration or component issues, the test will pass.
    }
}
