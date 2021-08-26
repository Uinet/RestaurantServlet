package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.OrderDAOImp;
import com.github.uinet.dao.imp.OrderDishDAOImp;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final OrderDAOImp orderDAOImp = DAOFactory.getInstance().createOrderDao();
    private final OrderDishDAOImp orderDishDAOImp = DAOFactory.getInstance().createOrderDishDao();

    public List<Order> findAll() {
        return orderDAOImp.findAll();
    }

    public List<Order> findAllByUser(User user){
        return orderDAOImp.findAllByUserId(user.getId());
    }

    public Order findById(Long id){
        return orderDAOImp.findById(id);
    }

    public void changeStatus(Long id, OrderStatus status){
        Order order = orderDAOImp.findById(id);
        if(order != null){
            order.setStatus(status);
            orderDAOImp.update(order);
        }
    }

    public Order save(Order orders){
        Order order = orderDAOImp.create(orders);
        orderDishDAOImp.saveAll(order.getOrderDishes()
                .stream()
                .peek(orderDishes -> orderDishes.setOrder(order))
                .collect(Collectors.toList()));
        return order;
    }
}
