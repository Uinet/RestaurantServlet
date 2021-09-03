package com.github.uinet.services;

import com.github.uinet.dao.imp.OrderDishDAOImp;
import com.github.uinet.exception.DAOException;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderDish;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderDishServiceTest {

    @Mock
    private OrderDishDAOImp orderDishDAOImp;

    private OrderDishService orderDishService;

    private OrderDish orderDish;

    @Before
    public void init(){
        orderDish = OrderDish.builder()
                .id(1l)
                .dishId(1l)
                .orderId(1l)
                .quantities(1)
                .build();
        orderDishService = new OrderDishService(orderDishDAOImp);
    }

    @Test
    public void getOrderDishesByOrderId() throws DAOException {
        List<OrderDish> orderDishList = new ArrayList<>();
        when(orderDishDAOImp.findByOrderId(orderDish.getOrderId())).thenReturn(orderDishList);

        assertEquals(orderDishList, orderDishService.getOrderDishesByOrderId(orderDish.getOrderId()));
    }

    @Test
    public void addDishesToOrder() {
        Order order = Order.builder().id(1l).build();
        when(orderDishDAOImp.create(orderDish)).thenReturn(orderDish);
        orderDishService.addDishesToOrder(Collections.singletonList(orderDish), order);
        assertEquals(order.getId(), orderDish.getOrderId());
    }
}