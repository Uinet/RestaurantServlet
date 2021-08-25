package com.github.uinet.model;

import jdk.nashorn.internal.ir.SplitReturn;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private LocalDateTime creationDate;
    private User customer;
    private List<Dish> dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Order(){}

    public Order(Long id, LocalDateTime creationDate, User customer, List<Dish> dishes) {
        this.id = id;
        this.creationDate = creationDate;
        this.customer = customer;
        this.dishes = dishes;
    }

    public static class Builder{
        private final Order order = new Order();

        public Builder id(Long id){
            order.setId(id);
            return this;
        }

        public Builder creationDate(LocalDateTime creationDate){
            order.setCreationDate(creationDate);
            return this;
        }

        public Builder customer(User user){
            order.setCustomer(user);
            return this;
        }

        public Builder dish(List<Dish> dishes){
            order.setDishes(dishes);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
