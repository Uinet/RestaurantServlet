package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.OrderDAOImp;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.model.User;

import java.util.List;

public class OrderService {
    private final OrderDAOImp orderDAOImp = DAOFactory.getInstance().createOrderDao();

    public List<Order> findAll() {
        return orderDAOImp.findAll();
    }

    public List<Order> findAllByUser(User user){
        return orderDAOImp.findAllByUserId(user.getId());
    }

    public Order findById(long id){
        return orderDAOImp.findById(id);
    }

    public void changeStatus(long id, OrderStatus status){
        Order order = orderDAOImp.findById(id);
        if(order != null){
            order.setStatus(status);
            orderDAOImp.update(order);
        }
    }

/*
    public double getOrderAmount(Order order){
        return new OrderDishService().getOrderDishesByOrderId(order.getId()).stream().
    }
*/

    public Order save(Order orders){
       return orderDAOImp.create(orders);
    }
}
