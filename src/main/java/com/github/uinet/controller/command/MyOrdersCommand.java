package com.github.uinet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class MyOrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/user/myorders.jsp";
    }
}
