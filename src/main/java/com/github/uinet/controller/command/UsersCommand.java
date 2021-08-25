package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;

import javax.servlet.http.HttpServletRequest;

public class UsersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        final UserDAOImp userDao = DAOFactory.getInstance().createUserDao();
        request.setAttribute("users",userDao.findAll());
        return "/WEB-INF/admin/users.jsp";
    }
}
