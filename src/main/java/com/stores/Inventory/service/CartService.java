package com.stores.Inventory.service;

import com.stores.Inventory.model.Cart;
import com.stores.Inventory.model.CartItem;
import com.stores.Inventory.model.Product;
import com.stores.Inventory.model.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    Cart cart = new Cart();

    public List<CartItem> addToCart(ProductDTO productDTO,int quantity){
        Product product = productDTO.toProduct();
        return cart.addProduct(product,quantity);
    }
}
