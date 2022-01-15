package com.bayzdelivery.service;

import com.bayzdelivery.configuration.DeliveryConsts;
import com.bayzdelivery.model.Order;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.repositories.OrderRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

    @Autowired
    DeliveryRepository deliveryRepository;


    @Override
    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList;
    }

    @Override
    public Order save(Order o) {
        o.setDeliverFlag(DeliveryConsts.DELIVERY_NOT_STARTED); // default value is delivery not started.
        return orderRepository.save(o);
    }

    @Override
    public Order findById(Long orderId) {
        Optional<Order> dbOrder = orderRepository.findById(orderId);
        return dbOrder.orElse(null);
    }

    @Override
    public List findDelayOrder(int delayTimeinMin) {
        Instant deadLine = Instant.now().minus(delayTimeinMin, ChronoUnit.MINUTES);
        return orderRepository.findOrdersByDeliverFlagEqualsAndOrderTimeBefore(DeliveryConsts.DELIVERY_NOT_STARTED,deadLine);
    }
}
