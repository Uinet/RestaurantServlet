package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UsersCommand implements Command{
    private final Logger logger = LogManager.getLogger(UsersCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("users",DAOFactory.getInstance().createUserDao().findAll());
        logger.info("Opening users page");
        return "/WEB-INF/admin/users.jsp";
    }
}
