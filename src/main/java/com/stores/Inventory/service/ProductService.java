package com.stores.Inventory.service;

import java.util.List;
import java.util.Optional;

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

    public Product save(ProductDTO productDTO){
        Product product = productRepository.save(productDTO.toProduct());
        System.out.println(product.toString());
        return product;
    }

    public String delete(long l) {
        Optional<Product> product = productRepository.findById(l);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return "The product was successfully Deleted.";
        }

        return "Can Not Delete None Existing Product.";
    }


    
}
