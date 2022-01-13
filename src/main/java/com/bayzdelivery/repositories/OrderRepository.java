package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by ozgurokka on 1/13/22 7:17 PM
 */
@RestResource(exported = false)
public interface OrderRepository extends CrudRepository<Order,Long> {
}
