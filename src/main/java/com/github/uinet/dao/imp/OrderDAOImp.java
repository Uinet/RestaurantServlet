package com.github.uinet.dao.imp;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.OrderDAO;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.utils.ConnectionCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImp implements OrderDAO {

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_SELECT_ALL_ORDERS_BY_USER = "SELECT * FROM orders where user_id=?";
    private static final String SQL_SELECT_ORDER_BY_ID ="SELECT * FROM orders WHERE id=?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders (status, user_id) VALUES (?, ?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET status=?, user_id=? WHERE id=?";

    private Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .customerId(resultSet.getLong("user_id"))
                .id(resultSet.getLong("id"))
                .status(OrderStatus.valueOf(resultSet.getString("status")))
                .build();
    }

    @Override
    public Order create(Order entity) {
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, entity.getStatus().toString());
            preparedStatement.setLong(2, entity.getCustomerId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Order findById(long orderId) {
        Order order = null;
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);){
            preparedStatement.setLong(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                order = extractFromResultSet(rs);
            }

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> resultList = new ArrayList<>();
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS)){
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
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER)){
            preparedStatement.setString(1,entity.getStatus().toString());
            preparedStatement.setLong(2, entity.getCustomer().getId());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Order entity) {

    }

    public List<Order> findAllByUserId(long id) {
        List<Order> result = new ArrayList<>();
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS_BY_USER);){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result.add(extractFromResultSet(rs));
            }

        }catch (SQLException ex){
            throw new RuntimeException();
        }
        return result;
    }
}
