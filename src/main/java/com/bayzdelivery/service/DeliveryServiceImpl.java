package com.bayzdelivery.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bayzdelivery.configuration.DeliveryConsts;
import com.bayzdelivery.model.Order;
import com.bayzdelivery.model.Person;
import com.bayzdelivery.model.ResponseModelTopDelivery;
import com.bayzdelivery.repositories.DeliveryRepository;
import com.bayzdelivery.model.Delivery;
import com.bayzdelivery.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  OrderRepository orderRepository;

  public Delivery save(Delivery delivery) {

    //check if order id consists
    Optional<Order> optionalOrder= orderRepository.findById(delivery.getOrder().getId());
    if (optionalOrder.isPresent()) {
      Order o = optionalOrder.get();
      delivery.setComission(new BigDecimal(o.getPrice().doubleValue() * 0.05 + delivery.getDistance() * 0.5));

      o.setDeliverFlag(DeliveryConsts.DELIVERY_DONE); // set order delivery flag done. so deliveryman can't pick again same delivery.
      orderRepository.save(o);  // in here db transaction need. @TODO
    }
    return deliveryRepository.save(delivery); // in here db transaction need. @TODO
  }

  public Delivery findById(Long deliveryId) {
    Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
    if (optionalDelivery.isPresent()) {
      return optionalDelivery.get();
    }else return null;
  }

  @Override
  public ResponseModelTopDelivery findTopDelivery(Instant from, Instant to, int max) {

    ResponseModelTopDelivery result = new ResponseModelTopDelivery();

    deliveryRepository.findDeliveriesByEndTimeBetween(from,to,PageRequest.of(0, max, Sort.by("order.price").descending())).
            forEach(delivery -> {
              result.getTopDeliverMan().add(delivery.getDeliveryMan());
              result.addTotalCommision(delivery.getComission());});


    return result;
  }

}
