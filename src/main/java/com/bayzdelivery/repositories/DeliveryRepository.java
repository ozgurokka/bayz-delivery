package com.bayzdelivery.repositories;

import com.bayzdelivery.model.Delivery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.Instant;
import java.util.List;

@RestResource(exported = false)
public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, Long> {
    List<Delivery> findDeliveriesByEndTimeBetween(Instant start, Instant end, Pageable pageable);
}

