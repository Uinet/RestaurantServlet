package com.github.uinet.dao.imp;

import com.github.uinet.dao.OrderDishDAO;
import com.github.uinet.model.OrderDish;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class OrderDishDAOImp implements OrderDishDAO {

    private Connection connection;

    public OrderDishDAOImp(Connection connection){
        this.connection = connection;
    }

    @Override
    public OrderDish create(OrderDish entity) {
        return null;
    }

    @Override
    public Optional<OrderDish> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderDish> findAll() {
        return null;
    }

    @Override
    public void update(OrderDish entity) {

    }

    @Override
    public void delete(OrderDish entity) {

    }

    @Override
    public void close() throws Exception {

    }

    public void saveAll(List<OrderDish> collect) {
    }
}
