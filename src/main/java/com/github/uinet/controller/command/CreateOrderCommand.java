package com.github.uinet.controller.command;

import com.github.uinet.model.Order;
import com.github.uinet.model.OrderDish;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.services.OrderDishService;
import com.github.uinet.services.OrderService;
import com.github.uinet.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = new OrderService().save(Order.builder()
                .customer(new UserService()
                        .loadUserByUsername((String) session.getServletContext().getAttribute("username")))
                .status(OrderStatus.NEW)
                .build());

        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");
        try {
            new OrderDishService().addDishesToOrder(orderDishList, order);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }

        session.setAttribute("orderDishes", new ArrayList<>());

        return "redirect:/app/user/myorders";
    }
}
