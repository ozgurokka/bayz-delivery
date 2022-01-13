package com.bayzdelivery.controller;

import com.bayzdelivery.model.Order;
import com.bayzdelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ozgurokka on 1/13/22 7:12 PM
 */

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(path = "/api/order")
    public ResponseEntity<Order> giveOrder(@RequestBody Order o) {
        return ResponseEntity.ok(orderService.save(o));
    }

    @GetMapping(path = "/api/order")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping(path = "/api/order/{order-id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name="order-id", required=true)Long orderId) {
        Order order = orderService.findById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/api/order/max/{max}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable(name="max", required=true)int max) {
        return ResponseEntity.ok(orderService.findTopNByOrderPriceDesc(max));
    }

}
