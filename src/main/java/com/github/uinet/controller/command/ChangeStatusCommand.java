package com.github.uinet.controller.command;

import com.github.uinet.exception.DAOException;
import com.github.uinet.model.OrderStatus;
import com.github.uinet.services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeStatusCommand implements Command{
    private final Logger logger = LogManager.getLogger(ChangeStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        try {
            new OrderService().changeStatus(Long.parseLong(request.getParameter("orderId")),
                    OrderStatus.valueOf(request.getParameter("status").toUpperCase()));
        } catch (DAOException e) {
            logger.error("Error of order status changed", e);
        }


        logger.info("The status of order number " +
                request.getParameter("orderId") +
                " would be changed to " +
                request.getParameter("status"));

        return "redirect:/app/admin/orders";
    }
}
