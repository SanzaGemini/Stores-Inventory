package com.stores.Inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.ProductDTO;
import com.stores.Inventory.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Product>> addProduct(@RequestBody ProductDTO productDTO){
        List<Product> newProduct = productService.save(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    
}
