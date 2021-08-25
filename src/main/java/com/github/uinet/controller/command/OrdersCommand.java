package com.github.uinet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class OrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/orders.jsp";
    }
}
