package com.bayzdelivery.model;

import com.bayzdelivery.configuration.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Created by ozgurokka on 1/13/22 8:00 PM
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {


    private static final long serialVersionUID = 123765351514001L;

    public Order(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "item")
    String item;

    @Column(name = "price")
    BigDecimal price;

    @NotNull
    @Column(name = "order_time")
    Instant orderTime;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    Person customer;

    @Column(name = "deliver_flag")
    int deliverFlag;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
    }


    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public int getDeliverFlag() {
        return deliverFlag;
    }

    public void setDeliverFlag(int deliverFlag) {
        this.deliverFlag = deliverFlag;
    }
}
