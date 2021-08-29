package com.github.uinet.controller.command;

import com.github.uinet.model.DishCategory;
import com.github.uinet.model.OrderDish;
import com.github.uinet.services.DishService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MenuCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        DishService dishService = new DishService();
        HttpSession session = request.getSession();

        int page = 1;
        int recordsPerPage = 6;

        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }

        if(request.getParameter("category") != null){
            request.setAttribute("dishes",
                    dishService.findAllByCategory(DishCategory.valueOf(request.getParameter("category")), page, recordsPerPage));
        } else {
            request.setAttribute("dishes", dishService.findAllDish(page, recordsPerPage));
        }

        List<OrderDish> orderDishes = (List<OrderDish>) session.getAttribute("orderDishes");
        if(orderDishes != null){
            session.setAttribute("orderSum", orderDishes.stream().map(OrderDish::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        int nOfPages = dishService.getNumbersOfRows() / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("pageNumbers", IntStream.range(1, nOfPages+1).boxed().collect(Collectors.toList()));
        request.setAttribute("currentPage", page);
        request.setAttribute("categories", DishCategory.values());

        return "/WEB-INF/user/menu.jsp";
    }
}
