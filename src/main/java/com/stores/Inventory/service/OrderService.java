package com.stores.Inventory.service;

import com.stores.Inventory.model.Order;
import com.stores.Inventory.model.dto.OrderDTO;
import com.stores.Inventory.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    public Order saveOrder(OrderDTO orderDTO){
        return orderRepository.save(orderDTO.toOrder());
    }

    public Order getOrderById(Long id){
        return orderRepository.findById(id).get();
    }
}
