package com.github.uinet.controller.command;

import com.github.uinet.exception.DAOException;
import com.github.uinet.model.User;
import com.github.uinet.services.OrderDishService;
import com.github.uinet.services.OrderService;
import com.github.uinet.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyOrdersCommand implements Command{
    private final Logger logger = LogManager.getLogger(MyOrdersCommand.class);
    @Override
    public String execute(HttpServletRequest request) {

        logger.info("Opening my orders page");

        OrderService orderService = new OrderService();
        User user = null;
        try {
            user = new UserService().loadUserByUsername((String)request
                            .getSession()
                            .getServletContext()
                            .getAttribute("username"));
        } catch (DAOException e) {
            logger.error("Error loading user by username", e);
        }

        int page = 1;
        int recordsPerPage = 5;

        if (request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            request.setAttribute("orders", orderService.findAllByUser(user, page, recordsPerPage));
        } catch (DAOException e) {
            logger.error("Error loading user orders", e);
        }
        request.setAttribute("orderDishService", new OrderDishService());

        int nOfPages = 0;
        try {
            nOfPages = orderService.getNumbersOfRowsByUser(user) / recordsPerPage;
        } catch (DAOException e) {
            logger.error("Error getting the number of rows", e);
        }
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("pageNumbers", IntStream.range(1, nOfPages+1).boxed().collect(Collectors.toList()));
        request.setAttribute("currentPage", page);

        return "/WEB-INF/user/myorders.jsp";
    }
}
