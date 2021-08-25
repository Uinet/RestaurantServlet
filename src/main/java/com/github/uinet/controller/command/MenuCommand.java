package com.github.uinet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class MenuCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/user/menu.jsp";
    }
}
