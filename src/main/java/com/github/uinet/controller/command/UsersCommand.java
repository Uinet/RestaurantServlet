package com.github.uinet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class UsersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/users.jsp";
    }
}
