package com.github.uinet.model;

import com.github.uinet.services.OrderDishService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private long id;
    private long customerId;
    private OrderStatus status;
    private LocalDateTime creationDateTime;

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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public BigDecimal getSum(){
        return new OrderDishService().getOrderDishesByOrderId(id).stream()
                .map(OrderDish::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Builder builder(){
        return new Builder();
    }

    public Order(){}

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

        public Builder creationDate(LocalDateTime creationDate){
            order.setCreationDateTime(creationDate);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
