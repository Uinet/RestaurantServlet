package com.github.uinet.controller.command;

import com.github.uinet.exception.DAOException;
import com.github.uinet.services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class PayCommand implements Command{
    private final Logger logger = LogManager.getLogger(PayCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();

        long orderId = Long.parseLong(request.getParameter("orderId"));

        try {
            orderService.payOrder(orderService.findById(orderId));
        } catch (DAOException e) {
            logger.error("Payment error", e);
        }

        return "redirect:/app/user/myorders";
    }
}
