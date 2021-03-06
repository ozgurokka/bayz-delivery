package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;


import java.time.Instant;
import java.util.List;

/**
 * Created by ozgurokka on 1/13/22 7:17 PM
 */
@RestResource(exported = false)
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
    List<Order> findOrdersByDeliverFlagEqualsAndOrderTimeBefore(Integer flag,Instant time);

}
