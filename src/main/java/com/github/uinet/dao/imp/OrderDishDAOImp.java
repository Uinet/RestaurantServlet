package com.github.uinet.dao.imp;

import com.github.uinet.dao.OrderDishDAO;
import com.github.uinet.exception.DAOException;
import com.github.uinet.model.OrderDish;
import com.github.uinet.utils.ConnectionCreator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDishDAOImp implements OrderDishDAO {

    private static final String SQL_CREATE_ORDER_DISH = "INSERT INTO order_dishes (quantities, dish_id, order_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_ORDER_DISH = "UPDATE order_dishes SET quantities=?, dish_id=?, order_id=? WHERE id=?";
    private static final String SQL_SELECT_ALL_ORDER_DISH_BY_ORDER = "SELECT * FROM order_dishes where order_id=?";

    private final Logger logger = LogManager.getLogger(OrderDishDAO.class);

    private OrderDish extractFromResultSet(ResultSet resultSet) throws SQLException {
        return OrderDish.builder()
                .id(resultSet.getLong("id"))
                .dishId(resultSet.getLong("dish_id"))
                .orderId(resultSet.getLong("order_id"))
                .quantities(resultSet.getInt("quantities"))
                .build();
    }

    @Override
    public OrderDish create(OrderDish entity){
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER_DISH, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, entity.getQuantities());
            preparedStatement.setLong(2, entity.getDishId());
            preparedStatement.setLong(3, entity.getOrderId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException ex) {
            logger.error("OrderDish create sql exception", ex);
        }

        return entity;
    }

    @Override
    public OrderDish findById(long id) {
        return new OrderDish();
    }

    @Override
    public List<OrderDish> findAll() {
        return null;
    }

    @Override
    public List<OrderDish> findAll(int page, int recordsPerPage) {
        return null;
    }

    @Override
    public void update(OrderDish entity) throws DAOException {
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_DISH);){
            preparedStatement.setInt(1, entity.getQuantities());
            preparedStatement.setLong(2, entity.getDishId());
            preparedStatement.setLong(3, entity.getOrderId());
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            logger.error("OrderDish update sql exception", e);
            throw new DAOException("OrderDish update sql exception", e);
        }
    }

    @Override
    public void delete(OrderDish entity) {

    }

    public void saveAll(List<OrderDish> collect) {
    }

    public List<OrderDish> findByOrderId(long orderId) throws DAOException {
        List<OrderDish> result = new ArrayList<>();
        try(Connection connection = ConnectionCreator.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_ORDER_DISH_BY_ORDER);){
            preparedStatement.setLong(1, orderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result.add(extractFromResultSet(rs));
            }

        }catch (SQLException e){
            logger.error("Find OrderDishes by order id exception", e);
            throw new DAOException("Find OrderDishes by order id exception", e);
        }
        return result;
    }
}
