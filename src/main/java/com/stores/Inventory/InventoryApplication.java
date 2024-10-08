package com.stores.Inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class InventoryApplication {

	@GetMapping()
	public String exampleString(){
		return "This is for test perpose";
	}
	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
