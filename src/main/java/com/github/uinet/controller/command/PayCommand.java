package com.github.uinet.controller.command;

import com.github.uinet.services.OrderService;

import javax.servlet.http.HttpServletRequest;

public class PayCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();

        long orderId = Long.parseLong(request.getParameter("orderId"));

        orderService.payOrder(orderService.findById(orderId));

        return "redirect:/app/user/myorders";
    }
}
