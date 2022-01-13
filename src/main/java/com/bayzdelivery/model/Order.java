package com.bayzdelivery.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    Long price;

    @ManyToOne
    @JoinColumn(name = "delivery_man_id", referencedColumnName = "id")
    Person deliveryMan;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    Person customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Person getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(Person deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(item, order.item) &&
                Objects.equals(price, order.price) &&
                Objects.equals(deliveryMan, order.deliveryMan) &&
                Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, price, deliveryMan, customer);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", deliveryMan=" + deliveryMan +
                ", customer=" + customer +
                '}';
    }
}
