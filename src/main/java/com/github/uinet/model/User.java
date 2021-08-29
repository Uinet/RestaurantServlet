package com.github.uinet.model;

import java.math.BigDecimal;

public class User {
    private long id;
    private String name;
    private String username;
    private String password;
    private UserRole role;
    private BigDecimal money;

    public long getId() {
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

    public void setId(long id) {
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public static Builder builder(){
        return new Builder();
    }

    public User(){}

    public User(long id, String name, String username, String password, UserRole role, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.money = money;
    }

    public static class Builder{
        private final User user = new User();

        public Builder id(long id){
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

        public Builder money(BigDecimal money){
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
