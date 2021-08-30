package com.github.uinet.controller.command;

import com.github.uinet.exception.UserException;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;
import com.github.uinet.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class RegistrationCommand implements Command{
    private final Logger logger = LogManager.getLogger(RegistrationCommand.class);
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
                    .money(new BigDecimal("0.0"))
                    .build());
            logger.info("User " + username + " was created");
        } catch (UserException e) {
            request.setAttribute("UserIsExist", true);
            logger.error("User " + username + " is exist error", e);
        }

        return "/registration.jsp";
    }
}
