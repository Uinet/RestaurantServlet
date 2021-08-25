package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;

import javax.servlet.http.HttpServletRequest;

public class UsersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users",DAOFactory.getInstance().createUserDao().findAll());
        return "/WEB-INF/admin/users.jsp";
    }
}
