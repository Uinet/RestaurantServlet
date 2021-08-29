package com.github.uinet.model;

public class Dish {
    private long id;
    private String name;
    private double price;
    private DishCategory category;
    private String description;
    private String img;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Dish(){}

    public Dish(Long id, String name, double price, DishCategory category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public static class Builder{
        private final Dish dish = new Dish();

        public Builder id(long id){
            dish.setId(id);
            return this;
        }

        public Builder name(String name){
            dish.setName(name);
            return this;
        }

        public Builder price(double price){
            dish.setPrice(price);
            return this;
        }

        public Builder category(DishCategory category){
            dish.setCategory(category);
            return this;
        }

        public Builder description(String description){
            dish.setDescription(description);
            return this;

        }

        public Builder image(String imageUrl){
            dish.setImg(imageUrl);
            return this;
        }

        public Dish build(){
            return dish;
        }
    }
}
