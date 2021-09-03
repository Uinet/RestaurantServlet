package com.github.uinet.model;

import com.github.uinet.exception.DAOException;
import com.github.uinet.services.DishService;

import java.math.BigDecimal;

public class OrderDish {
    private long id;
    private long orderId;
    private long dishId;
    private int quantities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getDishId() {
        return dishId;
    }

    public void setDishId(long dishId) {
        this.dishId = dishId;
    }

    public Dish getDish(){
        return new DishService().findDishById(dishId);
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public BigDecimal getTotalPrice(){
        return getDish().getPrice().multiply(BigDecimal.valueOf(quantities));
    }

    public void incrementQuantities(){
        quantities++;
    }

    public void decrementQuantities(){
        quantities--;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private final OrderDish orderDish;
        public Builder(){
            orderDish = new OrderDish();
        }

        public Builder id(long id){
            orderDish.setId(id);
            return this;
        }

        public Builder dishId(long dishId){
            orderDish.setDishId(dishId);
            return this;
        }

        public Builder orderId(long orderId){
            orderDish.setOrderId(orderId);
            return this;
        }

        public Builder quantities(int quantities){
            orderDish.setQuantities(quantities);
            return this;
        }

        public OrderDish build(){
            return orderDish;
        }
    }
}
