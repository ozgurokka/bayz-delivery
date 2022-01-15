package com.bayzdelivery.model;

import com.bayzdelivery.configuration.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "delivery")
public class Delivery implements Serializable{

  public Delivery() {
  }

  private static final long serialVersionUID = 123765351514001L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(View.General.class)
  Long id;

  @NotNull
  @Column(name = "start_time")
  @JsonView(View.DeliveryView.class)
  Instant startTime;

  @NotNull
  @JsonView(View.DeliveryView.class)
  @Column(name = "end_time")
  Instant endTime;

  @Column(name = "distance")
  @JsonView(View.DeliveryView.class)
  Long distance;

  @Column(name = "comission")
  @JsonView(View.DeliveryView.class)
  BigDecimal comission;

  @ManyToOne
  @JoinColumn(name = "delivery_man_id", referencedColumnName = "id")
  @JsonView(View.General.class)
  Person deliveryMan;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  @JsonView(View.General.class)
  Person customer;

  @OneToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  @JsonView(View.General.class)
  Order order;


  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getStartTime() {
    return startTime;
  }

  public void setStartTime(Instant startTime) {
    this.startTime = startTime;
  }

  public Instant getEndTime() {
    return endTime;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
  }

  public Long getDistance() {
    return distance;
  }

  public void setDistance(Long distance) {
    this.distance = distance;
  }

  public BigDecimal getComission() {
    return comission;
  }

  public void setComission(BigDecimal comission) {
    this.comission = comission;
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

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Delivery delivery = (Delivery) o;
    return Objects.equals(id, delivery.id) &&
            Objects.equals(startTime, delivery.startTime) &&
            Objects.equals(endTime, delivery.endTime) &&
            Objects.equals(distance, delivery.distance) &&
            Objects.equals(comission, delivery.comission) &&
            Objects.equals(deliveryMan, delivery.deliveryMan) &&
            Objects.equals(customer, delivery.customer) &&
            Objects.equals(order, delivery.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, startTime, endTime, distance, comission, deliveryMan, customer, order);
  }
}
