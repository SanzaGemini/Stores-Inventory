package com.stores.Inventory.controller;

import com.stores.Inventory.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CartController {

    @Autowired
    CartService cartService;



}
