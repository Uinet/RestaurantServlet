package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.OrderDishDAOImp;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderDish;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class OrderDishService {
    private final OrderDishDAOImp orderDishDAOImp = DAOFactory.getInstance().createOrderDishDao();

    public List<OrderDish> getOrderDishesByOrderId(long orderId){
        return orderDishDAOImp.findByOrderId(orderId);
    }

    public void addDishesToOrder(List<OrderDish> orderDishes, Order order) throws SQLIntegrityConstraintViolationException {
        System.out.println(order);
        for (OrderDish orderDish: orderDishes) {
            System.out.println(order);
            orderDish.setOrderId(order.getId());
            orderDishDAOImp.create(orderDish);
        }
    }
}
