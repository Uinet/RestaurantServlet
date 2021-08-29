package com.github.uinet.controller.command;

import com.github.uinet.model.OrderDish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RemoveFromCartCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        long dishId = Long.parseLong(request.getParameter("dishId"));

        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");

        orderDishList.removeIf(orderDish -> orderDish.getDishId() == dishId);

        return "redirect:/app/user/menu";
    }
}
