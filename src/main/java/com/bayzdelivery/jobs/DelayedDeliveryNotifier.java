package com.bayzdelivery.jobs;

import com.bayzdelivery.configuration.DeliveryConsts;
import com.bayzdelivery.service.OrderService;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bayzdelivery.model.Order;

import java.util.List;

@Component
public class DelayedDeliveryNotifier {

    private static final Logger LOG = LoggerFactory.getLogger(DelayedDeliveryNotifier.class);

    /**
     *  Use this method for the TASK 3
     */
    @Autowired
    OrderService orderService;

    @Scheduled(fixedDelay = 30000)
    public void checkDelayedDeliveries() {

        List<Order> delayedOrders = orderService.findDelayOrder(DeliveryConsts.DELIVERY_INTERVAL_IN_MIN);

        if(delayedOrders.size() > 0){
            notifyCustomerSupport(delayedOrders);
        }

    }


    /**
     * This method should be called to notify customer support team
     * It just writes notification on console but it may be email or push notification in real.
     * So that this method should run in an async way.
     */
    @Async
    public void notifyCustomerSupport(List<Order> delayedOrders) {

        //delayed order list will send in email or p.notification etc.
        LOG.info("Customer support team is notified! DelayedOrder Count "+ delayedOrders.size());
    }
}
