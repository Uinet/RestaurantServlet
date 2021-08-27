package com.github.uinet.controller.command;

import com.github.uinet.services.OrderDishService;
import com.github.uinet.services.OrderService;
import com.github.uinet.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class MyOrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders", new OrderService().findAllByUser(new UserService()
                .loadUserByUsername((String)request
                        .getSession()
                        .getServletContext()
                        .getAttribute("username"))));
        request.setAttribute("orderDishService", new OrderDishService());
        return "/WEB-INF/user/myorders.jsp";
    }
}
