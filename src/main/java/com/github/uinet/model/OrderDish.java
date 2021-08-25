package com.github.uinet.model;

public class OrderDish {
    private Long id;
    private Order order;
    private Dish dish;
    private int quantities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public Double getTotalPrice(){
        return getDish().getPrice()*quantities;
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

        public Builder id(Long id){
            orderDish.setId(id);
            return this;
        }

        public Builder order(Order order){
            orderDish.setOrder(order);
            return this;
        }

        public Builder dish(Dish dish){
            orderDish.setDish(dish);
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
