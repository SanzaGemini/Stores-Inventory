package com.stores.Inventory.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stores.Inventory.model.*;
import com.stores.Inventory.response.ApiResponse;
import com.stores.Inventory.service.ProductService;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<Object>> GetAllProducts(){
         return new ResponseEntity<>(ApiResponse.success(productService.findAllProducts()), HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<ApiResponse<Object>> AddProduct(@Valid @RequestBody ProductDTO productDTO){
        ResponseEntity<ApiResponse<Object>> reb= new ResponseEntity<>(ApiResponse.success(productService.save(productDTO)), HttpStatus.OK);
        return reb;
    }
    
}
