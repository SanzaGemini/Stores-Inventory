package com.stores.Inventory.controller;

import com.stores.Inventory.model.dto.ProductDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stores.Inventory.model.*;
import com.stores.Inventory.response.ApiResponse;
import com.stores.Inventory.service.ProductService;

import io.micrometer.common.lang.NonNull;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<Object>> GetAllProducts(){
         return new ResponseEntity<>(ApiResponse.success(productService.findAllProducts()), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Object>> updateProduct(@NonNull @PathVariable Long id,@Valid @RequestBody ProductDTO productDTO){
        Product product = productService.update(id,productDTO);
        return new ResponseEntity<>(ApiResponse.success(product),HttpStatus.OK);
    }


    @PostMapping("/product")
    public ResponseEntity<ApiResponse<Object>> AddProduct(@Valid @RequestBody ProductDTO productDTO){
         Product product = productService.save(productDTO);
        return new ResponseEntity<>(ApiResponse.success(product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteProduct(@NonNull @PathVariable Long id){
        return productService.delete(id) ? new ResponseEntity<>(ApiResponse.success("The product was successfully Deleted."),HttpStatus.OK)
            : new ResponseEntity<>(ApiResponse.failure("Can Not Delete None Existing Product."),HttpStatus.BAD_REQUEST);
    }
    
}
