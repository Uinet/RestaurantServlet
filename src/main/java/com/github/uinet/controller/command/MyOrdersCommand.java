package com.github.uinet.controller.command;

import com.github.uinet.model.User;
import com.github.uinet.services.OrderDishService;
import com.github.uinet.services.OrderService;
import com.github.uinet.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyOrdersCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {

        OrderService orderService = new OrderService();
        User user = new UserService().loadUserByUsername((String)request
                        .getSession()
                        .getServletContext()
                        .getAttribute("username"));

        int page = 1;
        int recordsPerPage = 5;

        if (request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }

        request.setAttribute("orders", orderService.findAllByUser(user, page, recordsPerPage));
        request.setAttribute("orderDishService", new OrderDishService());

        int nOfPages = orderService.getNumbersOfRowsByUser(user) / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("pageNumbers", IntStream.range(1, nOfPages+1).boxed().collect(Collectors.toList()));
        request.setAttribute("currentPage", page);

        return "/WEB-INF/user/myorders.jsp";
    }
}
