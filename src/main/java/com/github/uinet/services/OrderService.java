package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.OrderDAOImp;
import com.github.uinet.exception.DAOException;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.model.User;

import java.util.List;

public class OrderService {
    private final OrderDAOImp orderDAOImp;

    public OrderService(){
        orderDAOImp = DAOFactory.getInstance().createOrderDao();
    }

    public OrderService(OrderDAOImp orderDAOImp){
        this.orderDAOImp = orderDAOImp;
    }

    public List<Order> findAll(int page, int recordsPerPage) throws DAOException {
        return orderDAOImp.findAll(page, recordsPerPage);
    }

    public List<Order> findAllByUser(User user, int page, int recordsPerPage) throws DAOException {
        return orderDAOImp.findAllByUserId(user.getId(), page, recordsPerPage);
    }

    public Order findById(long id) throws DAOException {
        return orderDAOImp.findById(id);
    }

    public void changeStatus(long id, OrderStatus status) throws DAOException {
        Order order = orderDAOImp.findById(id);
        if(order != null){
            order.setStatus(status);
            orderDAOImp.update(order);
        }
    }

    public Order save(Order orders) throws DAOException {
       return orderDAOImp.create(orders);
    }

    public int getNumbersOfRows() throws DAOException {
        return orderDAOImp.getNumberOfRows();
    }

    public int getNumbersOfRowsByUser(User user) throws DAOException {
        return orderDAOImp.getNumberOfRowsByUser(user);
    }

    public void payOrder(Order order) throws DAOException {
        orderDAOImp.payOrder(order);
    }
}
