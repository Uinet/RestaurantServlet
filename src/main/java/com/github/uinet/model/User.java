package com.github.uinet.model;

public class User {
    private Long id;
    private String name;
    private String username;
    private String password;
    private UserRole role;
    private double money;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public double getMoney() {
        return money;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public static Builder builder(){
        return new Builder();
    }

    public User(){}

    public User(Long id, String name, String username, String password, UserRole role, double money) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.money = money;
    }

    public static class Builder{
        private final User user = new User();

        public Builder id(Long id){
            user.setId(id);
            return this;
        }

        public Builder name(String name){
            user.setName(name);
            return this;
        }

        public Builder username(String username){
            user.setUsername(username);
            return this;
        }

        public Builder password(String password){
            user.setPassword(password);
            return this;
        }

        public Builder money(double money){
            user.setMoney(money);
            return this;
        }

        public Builder role(UserRole role){
            user.setRole(role);
            return this;
        }

        public User build(){
            return user;
        }
    }
}
