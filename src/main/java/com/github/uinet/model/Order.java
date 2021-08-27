package com.github.uinet.model;

import com.github.uinet.services.OrderDishService;

public class Order {
    private long id;
    private long customerId;
    private OrderStatus status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getSum(){
        return new OrderDishService().getOrderDishesByOrderId(id).stream()
                .mapToDouble(OrderDish::getTotalPrice)
                .sum();
    }

    public static Builder builder(){
        return new Builder();
    }

    public Order(){}

    public Order(long id, User customer) {
        this.id = id;
        this.customerId = customer.getId();
    }

    public static class Builder{
        private final Order order = new Order();

        public Builder id(Long id){
            order.setId(id);
            return this;
        }

        public Builder customerId(long userId){
            order.setCustomerId(userId);
            return this;
        }

        public Builder status(OrderStatus orderStatus){
            order.setStatus(orderStatus);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
