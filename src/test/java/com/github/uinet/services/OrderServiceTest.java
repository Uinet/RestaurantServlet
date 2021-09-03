package com.github.uinet.services;

import com.github.uinet.dao.imp.OrderDAOImp;
import com.github.uinet.exception.DAOException;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderDAOImp orderDAOImp;

    private OrderService orderService;

    private Order order;

    @Before
    public void init(){
        order = Order.builder()
                .id(1l)
                .creationDate(LocalDateTime.now())
                .customerId(1l)
                .status(OrderStatus.NEW)
                .build();
        orderService = new OrderService(orderDAOImp);
    }

    @Test
    public void findById() throws DAOException {
        when(orderDAOImp.findById(order.getId())).thenReturn(order);
        assertEquals(order, orderService.findById(order.getId()));
    }

    @Test
    public void changeStatus() throws DAOException {
        when(orderDAOImp.findById(order.getId())).thenReturn(order);
        orderService.changeStatus(order.getId(), OrderStatus.CLOSED);
        assertEquals(OrderStatus.CLOSED, order.getStatus());
    }

    @Test
    public void save() throws DAOException {
        when(orderDAOImp.create(order)).thenReturn(order);
        assertEquals(order, orderService.save(order));
    }

    @Test
    public void getNumbersOfRows() throws DAOException {
        when(orderDAOImp.getNumberOfRows()).thenReturn(10);
        assertEquals(10, orderService.getNumbersOfRows());
    }

    @Test
    public void getNumbersOfRowsByUser() throws DAOException {
        User user = new User();
        when(orderDAOImp.getNumberOfRowsByUser(user)).thenReturn(10);

        assertEquals(10, orderService.getNumbersOfRowsByUser(user));
    }

    //TODO finish the test
    @Test
    public void payOrder() {

    }
}