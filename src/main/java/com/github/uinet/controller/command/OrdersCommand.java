package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;

public class OrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders", DAOFactory.getInstance().createOrderDao().findAll());
        return "/WEB-INF/admin/orders.jsp";
    }
}
