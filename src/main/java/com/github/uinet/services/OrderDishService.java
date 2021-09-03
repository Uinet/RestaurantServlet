package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.OrderDishDAOImp;
import com.github.uinet.exception.DAOException;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderDish;

import java.util.List;

public class OrderDishService {
    private final OrderDishDAOImp orderDishDAOImp;

    public OrderDishService(){
        orderDishDAOImp = DAOFactory.getInstance().createOrderDishDao();
    }

    public OrderDishService(OrderDishDAOImp orderDishDAOImp){
        this.orderDishDAOImp = orderDishDAOImp;
    }

    public List<OrderDish> getOrderDishesByOrderId(long orderId) throws DAOException {
        return orderDishDAOImp.findByOrderId(orderId);
    }

    public void addDishesToOrder(List<OrderDish> orderDishes, Order order){
        for (OrderDish orderDish: orderDishes) {
            orderDish.setOrderId(order.getId());
            orderDishDAOImp.create(orderDish);
        }
    }
}
