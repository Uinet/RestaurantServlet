package com.github.uinet.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command{
    @Override
    public String execute(HttpServletRequest request) {

        //Todo Service Registration Form
        return "/registration.jsp";
    }
}
