package com.stores.Inventory.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stores.Inventory.model.*;
import com.stores.Inventory.service.ProductService;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> GetAllProducts(){
        return productService.findAllProducts();
    }


    @PostMapping("/product")
    public List<Product> AddProduct(@Valid @RequestBody ProductDTO productDTO){
        return productService.save(productDTO);
    }
    
}
