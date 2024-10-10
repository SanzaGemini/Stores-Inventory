package com.stores.Inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.ProductDTO;
import com.stores.Inventory.repository.ProductRepository;
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> save(ProductDTO productDTO){
        productRepository.save(productDTO.toProduct());
        return findAllProducts();
    }
    
}
