package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UsersCommand implements Command{
    private final Logger logger = LogManager.getLogger(UsersCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.setAttribute("users",DAOFactory.getInstance().createUserDao().findAll());
        } catch (DAOException e) {
            logger.error("Error getting the list of users");
        }
        logger.info("Opening users page");
        return "/WEB-INF/admin/users.jsp";
    }
}
