package com.github.uinet.controller.command;

import com.github.uinet.model.OrderDish;
import com.github.uinet.services.DishService;
import com.github.uinet.services.OrderDishService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddToCartCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        Long dishId = Long.parseLong(request.getParameter("dishId"));

        HttpSession session = request.getSession();
        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");
        if(orderDishList == null){
            orderDishList = new ArrayList<>();
            session.setAttribute("orderDishes", orderDishList);
        }

        Optional<OrderDish> orderDish = orderDishList.stream().filter(orderD-> orderD.getDish().getId() == dishId).findFirst();

        if(orderDish.isPresent()){
            orderDish.get().incrementQuantities();
        } else {
            orderDishList.add(OrderDish.builder()
                    .dish(new DishService().findDishById(dishId))
                    .quantities(1)
                    .build());
        }

        return "redirect:/app/user/menu";
    }
}
