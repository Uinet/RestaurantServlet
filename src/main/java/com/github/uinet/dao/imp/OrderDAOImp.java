package com.github.uinet.dao.imp;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.OrderDAO;
import com.github.uinet.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImp implements OrderDAO {

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_SELECT_ALL_ORDERS_BY_USER = "SELECT * FROM orders where user_id=?";

    private final Connection connection;

    public OrderDAOImp(Connection connection){
        this.connection = connection;
    }

    private Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .customer(DAOFactory.getInstance().createUserDao().findById(resultSet.getLong("user_id")))
                .id(resultSet.getLong("id"))
                .orderDishes(DAOFactory.getInstance().createOrderDishDao().findByOrderId(resultSet.getLong("id")))
                .build();
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Order findById(Long orderId) {
        return new Order();
    }

    @Override
    public List<Order> findAll() {
        List<Order> resultList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ){
                Order result = extractFromResultSet(resultSet);
                resultList.add(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
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
