package com.bayzdelivery.service;

import com.bayzdelivery.model.Order;

import java.util.List;


/**
 * Created by ozgurokka on 1/13/22 7:18 PM
 */


public interface OrderService {


    public List<Order> getAll();

    public Order save(Order o);

    public Order findById(Long orderId);

    public List<Order> findTopNByOrderPriceDesc(int max);


}
