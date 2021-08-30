package com.github.uinet.controller.command;

import com.github.uinet.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ReplenishmentCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        BigDecimal sum = new BigDecimal(request.getParameter("money"));
        long userId = Long.parseLong(request.getParameter("userId"));

        UserService userService = new UserService();
        userService.topUpBalance(userId, sum);

        return "redirect:/app/admin/users";
    }
}
