package com.bayzdelivery.controller;

import com.bayzdelivery.configuration.View;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.ResponseModelTopDelivery;
import com.bayzdelivery.service.DeliveryService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @PostMapping(path ="/api/delivery")
  public ResponseEntity<Delivery> createNewDelivery(@RequestBody @JsonView(value = View.CreateDeliveryView.class) Delivery delivery) {
    return ResponseEntity.ok(deliveryService.save(delivery));
  }

  @GetMapping(path = "/api/delivery/{delivery-id}")
  public ResponseEntity<Delivery> pickDelivery(@PathVariable(name="delivery-id",required=true)Long deliveryId){
    Delivery delivery = deliveryService.findById(deliveryId);
    if (delivery !=null)
      return ResponseEntity.ok(delivery);
    return ResponseEntity.notFound().build();
  }

  @GetMapping(path = "/api/delivery/getMaxOrderPriceDelivery/from/{start-time}/to/{end-time}/max/{max}")
  public ResponseEntity<ResponseModelTopDelivery> getAllOrders(@PathVariable(name="start-time", required=true) Instant from, @PathVariable(name="end-time", required=true) Instant to, @PathVariable(name="max", required=true) int max) {
    return ResponseEntity.ok(deliveryService.findTopDelivery(from,to,max));
  }
}