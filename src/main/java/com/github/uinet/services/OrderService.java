package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.OrderDAOImp;
import com.github.uinet.dao.imp.OrderDishDAOImp;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.model.User;

import java.util.List;
import java.util.Optional;
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

    public Optional<Order> findById(Long id){
        return orderDAOImp.findById(id);
    }

    public OrderStatus changeStatus(Long id, OrderStatus status){
        Optional<Order> order = orderDAOImp.findById(id);
        if(order.isPresent()){
            order.get().setStatus(status);
            orderDAOImp.create(order.get());
        }
        return order.get().getStatus();
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
