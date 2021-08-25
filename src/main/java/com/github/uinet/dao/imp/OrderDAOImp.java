package com.github.uinet.dao.imp;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.OrderDAO;
import com.github.uinet.model.Order;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return Order.builder()
                .id(resultSet.getLong("id"))
                .customer(DAOFactory.getInstance()
                        .createUserDao()
                        .findById(resultSet.getLong("user_id"))
                        .get())
                .build();
    }

    @Override
    public void create(Order entity) {

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
}
