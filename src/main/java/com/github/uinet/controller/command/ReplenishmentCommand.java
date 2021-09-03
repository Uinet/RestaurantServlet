package com.github.uinet.controller.command;

import com.github.uinet.exception.DAOException;
import com.github.uinet.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ReplenishmentCommand implements Command{
    private final Logger logger = LogManager.getLogger(ReplenishmentCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        BigDecimal sum = new BigDecimal(request.getParameter("money"));
        long userId = Long.parseLong(request.getParameter("userId"));

        UserService userService = new UserService();
        try {
            userService.topUpBalance(userId, sum);
        } catch (DAOException e) {
            logger.error("Account replenishment error", e);
        }

        return "redirect:/app/admin/users";
    }
}
