package com.github.uinet.dao.imp;

import com.github.uinet.dao.OrderDishDAO;
import com.github.uinet.model.OrderDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDishDAOImp implements OrderDishDAO {

    private static final String SQL_CREATE_ORDER_DISH = "INSERT INTO order_dishes (quantities, dish_id, order_id) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_ORDER_DISH = "UPDATE order_dishes SET quantities=?, dish_id=?, order_id=? WHERE id=?";

    private Connection connection;

    public OrderDishDAOImp(Connection connection){
        this.connection = connection;
    }

    @Override
    public OrderDish create(OrderDish entity) throws SQLIntegrityConstraintViolationException  {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER_DISH, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setInt(1, entity.getQuantities());
            preparedStatement.setLong(2, entity.getDish().getId());
            preparedStatement.setLong(3, entity.getOrder().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException ex) {
            if(ex.getClass().equals(SQLIntegrityConstraintViolationException.class)){
                throw new SQLIntegrityConstraintViolationException(ex);
            }
        }

        return entity;
    }

    @Override
    public OrderDish findById(Long id) {
        return new OrderDish();
    }

    @Override
    public List<OrderDish> findAll() {
        return null;
    }

    @Override
    public void update(OrderDish entity) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_DISH);){
            preparedStatement.setInt(1, entity.getQuantities());
            preparedStatement.setLong(2, entity.getDish().getId());
            preparedStatement.setLong(3, entity.getOrder().getId());
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(OrderDish entity) {

    }

    @Override
    public void close() throws Exception {

    }

    public void saveAll(List<OrderDish> collect) {
    }

    public List<OrderDish> findByOrderId(Long orderId) {
        return new ArrayList<>();
    }
}
