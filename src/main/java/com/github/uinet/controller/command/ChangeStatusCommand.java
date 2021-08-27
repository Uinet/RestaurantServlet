package com.github.uinet.controller.command;

import com.github.uinet.model.OrderStatus;
import com.github.uinet.services.OrderService;

import javax.servlet.http.HttpServletRequest;

public class ChangeStatusCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {

        new OrderService().changeStatus(Long.parseLong(request.getParameter("orderId")),
                OrderStatus.valueOf(request.getParameter("status").toUpperCase()));

        return "redirect:/app/admin/orders";
    }
}
