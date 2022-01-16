package com.bayzdelivery.service;

import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.model.ResponseModelTopDelivery;

import java.time.Instant;

public interface DeliveryService {

  public Delivery save(Delivery delivery);

  public Delivery findById(Long deliveryId);

  public ResponseModelTopDelivery findTopDelivery(Instant from, Instant to, int max);
}
