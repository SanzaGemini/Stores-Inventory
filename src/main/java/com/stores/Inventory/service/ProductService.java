package com.stores.Inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stores.Inventory.entity.Product;
import com.stores.Inventory.repository.ProductRepository;
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> save(){
        Product product = new Product("car","BMW",(float) 10000.0,2);
        productRepository.save(product);
        return findAllProducts();
    }
    
}
