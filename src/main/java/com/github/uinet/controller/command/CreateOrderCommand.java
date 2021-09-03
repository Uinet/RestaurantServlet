package com.github.uinet.controller.command;

import com.github.uinet.exception.DAOException;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderCommand implements Command{
    private final Logger logger = LogManager.getLogger(CreateOrderCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = null;

        try {
            order = new OrderService().save(Order.builder()
                    .customerId(new UserService()
                            .loadUserByUsername((String) session.getServletContext().getAttribute("username")).getId())
                    .status(OrderStatus.NEW)
                    .creationDate(LocalDateTime.now())
                    .build());
        } catch (DAOException e) {
            logger.error("Order creation error", e);
        }

        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");
        new OrderDishService().addDishesToOrder(orderDishList, order);
        logger.info("A new order has been created");

        session.setAttribute("orderDishes", new ArrayList<>());

        return "redirect:/app/user/myorders";
    }
}
