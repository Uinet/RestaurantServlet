package com.github.uinet.controller.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command{
    private final Logger logger = LogManager.getLogger(LogOutCommand.class);
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("role");
        session.getServletContext().removeAttribute("username");

        logger.info("User logged out");

        return "redirect:/index.jsp";
    }
}
