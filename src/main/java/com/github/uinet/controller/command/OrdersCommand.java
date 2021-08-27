package com.github.uinet.controller.command;

import com.github.uinet.services.OrderService;

import javax.servlet.http.HttpServletRequest;

public class OrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders", new OrderService().findAll());
        return "/WEB-INF/admin/orders.jsp";
    }
}
