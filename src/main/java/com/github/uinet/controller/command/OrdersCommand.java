package com.github.uinet.controller.command;

import com.github.uinet.exception.DAOException;
import com.github.uinet.services.OrderDishService;
import com.github.uinet.services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrdersCommand implements Command{
    private final Logger logger = LogManager.getLogger(OrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        logger.info("Opening orders page");

        OrderService orderService = new OrderService();

        int page = 1;
        int recordsPerPage = 5;

        if (request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }

        int nOfPages = 0;
        try {
            nOfPages = orderService.getNumbersOfRows() / recordsPerPage;
        } catch (DAOException e) {
            logger.error("Error getting the number of rows", e);
        }
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("pageNumbers", IntStream.range(1, nOfPages+1).boxed().collect(Collectors.toList()));
        request.setAttribute("currentPage", page);

        try {
            request.setAttribute("orders", orderService.findAll(page, recordsPerPage));
        } catch (DAOException e) {
            logger.error("Error loading orders from the database");
        }
        request.setAttribute("orderDishService", new OrderDishService());


        return "/WEB-INF/admin/orders.jsp";
    }
}
