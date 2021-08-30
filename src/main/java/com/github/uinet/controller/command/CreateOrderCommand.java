package com.github.uinet.controller.command;

import com.github.uinet.dao.DishDAO;
import com.github.uinet.model.Order;
import com.github.uinet.model.OrderDish;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.services.OrderDishService;
import com.github.uinet.services.OrderService;
import com.github.uinet.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderCommand implements Command{
    private final Logger logger = LogManager.getLogger(CreateOrderCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = new OrderService().save(Order.builder()
                .customerId(new UserService()
                        .loadUserByUsername((String) session.getServletContext().getAttribute("username")).getId())
                .status(OrderStatus.NEW)
                .build());

        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");
        new OrderDishService().addDishesToOrder(orderDishList, order);
        logger.info("A new order has been created");

        session.setAttribute("orderDishes", new ArrayList<>());

        return "redirect:/app/user/myorders";
    }
}
