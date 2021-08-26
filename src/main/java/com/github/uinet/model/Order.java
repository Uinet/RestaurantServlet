package com.github.uinet.model;

import jdk.nashorn.internal.ir.SplitReturn;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private User customer;
    private List<OrderDish> orderDishes;
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderDish> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Order(){}

    public Order(Long id, LocalDateTime creationDate, User customer, List<OrderDish> orderDishes) {
        this.id = id;
        this.customer = customer;
        this.orderDishes = orderDishes;
    }

    public static class Builder{
        private final Order order = new Order();

        public Builder id(Long id){
            order.setId(id);
            return this;
        }

        public Builder customer(User user){
            order.setCustomer(user);
            return this;
        }

        public Builder orderDishes(List<OrderDish> orderDishes){
            order.setOrderDishes(orderDishes);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
