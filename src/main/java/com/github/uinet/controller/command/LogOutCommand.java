package com.github.uinet.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");

        return "redirect:/index.jsp";
    }
}
