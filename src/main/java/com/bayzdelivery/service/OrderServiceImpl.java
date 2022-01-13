package com.bayzdelivery.service;

import com.bayzdelivery.model.Order;
import com.bayzdelivery.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ozgurokka on 1/13/22 7:21 PM
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;


    @Override
    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList;
    }

    @Override
    public Order save(Order o) {
        return orderRepository.save(o);
    }

    @Override
    public Order findById(Long orderId) {
        Optional<Order> dbOrder = orderRepository.findById(orderId);
        return dbOrder.orElse(null);
    }

}
