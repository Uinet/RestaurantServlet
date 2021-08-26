package com.github.uinet.dao.imp;

import com.github.uinet.dao.OrderDAO;
import com.github.uinet.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImp implements OrderDAO {

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_SELECT_ALL_ORDERS_BY_USER = "SELECT * FROM orders where user_id=?";

    private final Connection connection;

    public OrderDAOImp(Connection connection){
        this.connection = connection;
    }

    private Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Order();
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Order entity) {

    }

    @Override
    public void close() throws Exception {

    }

    public List<Order> findAllByUserId(Long id) {
        return new ArrayList<>();
    }
}
