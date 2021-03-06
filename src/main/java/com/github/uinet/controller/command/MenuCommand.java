package com.github.uinet.controller.command;

import com.github.uinet.exception.DAOException;
import com.github.uinet.model.DishCategory;
import com.github.uinet.model.OrderDish;
import com.github.uinet.services.DishService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuCommand implements Command{
    private final Logger logger = LogManager.getLogger(MenuCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        logger.info("Opening menu page");

        DishService dishService = new DishService();
        HttpSession session = request.getSession();

        int page = 1;
        int recordsPerPage = 6;

        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
            if(request.getParameter("category") != null){
                request.setAttribute("dishes",
                        dishService.findAllByCategory(DishCategory.valueOf(request.getParameter("category")),
                                page, recordsPerPage));
            } else if(request.getParameter("sortField") != null && request.getParameter("sortDirection") != null){
                request.setAttribute("dishes", dishService.getSortedDishes(request.getParameter("sortField"),
                        request.getParameter("sortDirection")));
            } else {
                request.setAttribute("dishes", dishService.findAllDish(page, recordsPerPage));
            }
        } catch (DAOException e){
            logger.error("Error loading menu from database", e);
        }



        List<OrderDish> orderDishes = (List<OrderDish>) session.getAttribute("orderDishes");
        if(orderDishes != null){
            session.setAttribute("orderSum", orderDishes.stream().map(OrderDish::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        int nOfPages = 0;
        try {
            nOfPages = dishService.getNumbersOfRows() / recordsPerPage;
        } catch (DAOException e) {
            logger.error("Error getting the number of rows", e);
        }
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("pageNumbers", IntStream.range(1, nOfPages+1).boxed().collect(Collectors.toList()));
        request.setAttribute("currentPage", page);
        request.setAttribute("categories", DishCategory.values());

        return "/WEB-INF/user/menu.jsp";
    }
}
