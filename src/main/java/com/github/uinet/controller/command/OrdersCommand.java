package com.github.uinet.controller.command;

import com.github.uinet.services.OrderDishService;
import com.github.uinet.services.OrderService;

import javax.servlet.http.HttpServletRequest;

public class OrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders", new OrderService().findAll());
        request.setAttribute("orderDishService", new OrderDishService());
        return "/WEB-INF/admin/orders.jsp";
    }
}
