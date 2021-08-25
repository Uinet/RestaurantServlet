package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;

public class MenuCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("dishes", DAOFactory.getInstance().createDishDao().findAll());
        return "/WEB-INF/user/menu.jsp";
    }
}
