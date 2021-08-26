package com.github.uinet.controller.command;

import com.github.uinet.exception.UserException;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;
import com.github.uinet.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        try {
            userService.registerNewUser(User.builder()
                    .role(UserRole.CLIENT)
                    .username(username)
                    .name(name)
                    .password(password)
                    .money(0.0)
                    .build());
        } catch (UserException e) {
            request.setAttribute("UserIsExist", true);
            e.printStackTrace();
        }

        //Todo Registration Error
        return "/registration.jsp";
    }
}
