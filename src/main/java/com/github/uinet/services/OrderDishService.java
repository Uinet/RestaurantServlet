package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.OrderDishDAOImp;
import com.github.uinet.model.Dish;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderDish;

import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class OrderDishService {
    private final OrderDishDAOImp orderDishDAOImp = DAOFactory.getInstance().createOrderDishDao();

    public OrderDish createOrderDish(Dish dish, Order order) throws SQLIntegrityConstraintViolationException {
        return orderDishDAOImp.create(OrderDish.builder()
                .dish(dish)
                .order(order)
                .quantities(1)
                .build());
    }

    public void updateOrderDish(OrderDish orderDish){
        orderDishDAOImp.update(orderDish);
    }

    public List<OrderDish> getOrderDishesByOrderId(Long orderId){
        return orderDishDAOImp.findByOrderId(orderId);
    }
}
