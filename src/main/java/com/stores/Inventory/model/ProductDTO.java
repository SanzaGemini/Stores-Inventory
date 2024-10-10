package com.stores.Inventory.model;


public class ProductDTO {

    private String name;
    
    private String description;
    
    private float price;
    
    
    private int quantity;
    
        
    public Product toProduct() {
        return new Product(name,description,price,quantity);
    }

}
