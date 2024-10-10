package com.stores.Inventory.Unittest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.stores.Inventory.InventoryApplication;

@SpringBootTest
@ContextConfiguration(classes = InventoryApplication.class)
class InventoryApplicationTests {

	@Test
	void contextLoads() {
	}

}
