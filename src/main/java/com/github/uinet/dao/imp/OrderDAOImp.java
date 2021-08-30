package com.github.uinet.dao.imp;

import com.github.uinet.dao.OrderDAO;
import com.github.uinet.dao.UserDAO;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.model.User;
import com.github.uinet.utils.ConnectionCreator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImp implements OrderDAO {

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_SELECT_ALL_ORDERS_BY_USER = "SELECT * FROM orders WHERE user_id=? ORDER BY id DESC LIMIT ?, ?";
    private static final String SQL_SELECT_ORDER_BY_ID ="SELECT * FROM orders WHERE id=?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders (status, user_id) VALUES (?, ?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET status=?, user_id=? WHERE id=?";
    private static final String SQL_SELECT_PAGINATION_ORDERS = "SELECT * FROM orders ORDER BY id DESC LIMIT ?, ?";
    private static final String SQL_GET_COUNT_OF_ORDERS = "SELECT COUNT(id) AS row_count FROM orders";
    private static final String SQL_GET_COUNT_OF_ORDERS_BY_USER = "SELECT COUNT(id) AS row_count FROM orders WHERE user_id=?";
    private static final String SQL_CHANGE_ORDER_STATUS_TO_PAID = "UPDATE orders SET status='PAID' WHERE id=?";
    private static final String SQL_INCREASE_ADMIN_MONEY = "UPDATE users SET money=money+? WHERE role='MANAGER' LIMIT 1";
    private static final String SQL_REDUCE_CUSTOMER_MONEY = "UPDATE users SET money=money-? WHERE id=?";

    private final Logger logger = LogManager.getLogger(OrderDAO.class);

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
            logger.error("Create new order exception", e);
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

        }catch (SQLException e){
            logger.error("Find order by id exception", e);
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAll(int page, int recordsPerPage) {
        List<Order> resultList = new ArrayList<>();
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PAGINATION_ORDERS)){
            preparedStatement.setInt(1, page * recordsPerPage - recordsPerPage);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ( resultSet.next() ){
                Order result = extractFromResultSet(resultSet);
                resultList.add(result);
            }
        } catch (Exception e) {
            logger.error("Find all orders exception", e);
        }
        return resultList;
    }

    @Override
    public void update(Order entity) {
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER)){
            preparedStatement.setString(1,entity.getStatus().toString());
            preparedStatement.setLong(2, entity.getCustomerId());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            logger.error("Order update exception", e);
        }
    }

    @Override
    public void delete(Order entity) {

    }

    public List<Order> findAllByUserId(long id, int page, int recordsPerPage) {
        List<Order> result = new ArrayList<>();
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS_BY_USER);){
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, page * recordsPerPage - recordsPerPage);
            preparedStatement.setInt(3, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result.add(extractFromResultSet(rs));
            }

        }catch (SQLException e){
            logger.error("Find all orders by user id exception", e);
        }
        return result;
    }

    public int getNumberOfRows(){
        int result = 0;
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_OF_ORDERS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt("row_count");
            }
        }
        catch (SQLException e){
            logger.error("Get orders count exception", e);
        }
        return result;
    }

    public int getNumberOfRowsByUser(User user) {
        int result = 0;
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_OF_ORDERS_BY_USER)){
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getInt("row_count");
            }
        }
        catch (SQLException e){
            logger.error("Get orders count by user exception", e);
        }
        return result;
    }

    public void payOrder(Order order){
        Connection connection = null;
        PreparedStatement decreaseMoneyStatement = null;
        PreparedStatement increaseMoneyStatement = null;
        PreparedStatement changeStatusStatement = null;
        try {
            connection = ConnectionCreator.createConnection();
            connection.setAutoCommit(false);

            changeStatusStatement = connection.prepareStatement(SQL_CHANGE_ORDER_STATUS_TO_PAID);
            changeStatusStatement.setLong(1, order.getId());
            changeStatusStatement.executeUpdate();

            decreaseMoneyStatement = connection.prepareStatement(SQL_REDUCE_CUSTOMER_MONEY);
            decreaseMoneyStatement.setBigDecimal(1, order.getSum());
            decreaseMoneyStatement.setLong(2, order.getCustomerId());
            decreaseMoneyStatement.executeUpdate();

            increaseMoneyStatement = connection.prepareStatement(SQL_INCREASE_ADMIN_MONEY);
            increaseMoneyStatement.setBigDecimal(1, order.getSum());
            increaseMoneyStatement.executeUpdate();

            connection.commit();

        } catch (SQLException ex){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }finally {
            try {
                decreaseMoneyStatement.close();
                increaseMoneyStatement.close();
                changeStatusStatement.close();
                connection.close();

            } catch (SQLException e) {
                logger.error("Error closing connection", e);
            }
        }
    }
}
