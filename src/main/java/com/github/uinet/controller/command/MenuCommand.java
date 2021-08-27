package com.github.uinet.controller.command;

import com.github.uinet.model.DishCategory;
import com.github.uinet.model.OrderDish;
import com.github.uinet.services.DishService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MenuCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        DishService dishService = new DishService();

        if(request.getParameter("category") != null){
            request.setAttribute("dishes",
                    dishService.findAllByCategory(DishCategory.valueOf(request.getParameter("category"))));
        } else {
            request.setAttribute("dishes", dishService.findAllDish());
        }

        HttpSession session = request.getSession();
        List<OrderDish> orderDishes = (List<OrderDish>) session.getAttribute("orderDishes");
        if(orderDishes != null){
            session.setAttribute("orderSum", orderDishes.stream().mapToDouble(OrderDish::getTotalPrice).sum());
        }
        request.setAttribute("categories", DishCategory.values());
        return "/WEB-INF/user/menu.jsp";
    }
}
