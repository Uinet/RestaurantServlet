package com.github.uinet.controller.command;

import com.github.uinet.model.OrderDish;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ReduceFromCartCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        long dishId = Long.parseLong(request.getParameter("dishId"));

        HttpSession session = request.getSession();

        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");
        Optional<OrderDish> orderDishOptional = orderDishList.stream()
                .filter(orderDishes -> orderDishes.getDish().getId() == dishId)
                .findFirst();

        if(orderDishOptional.isPresent()){
            if (orderDishOptional.get().getQuantities() == 1){
                orderDishList.remove(orderDishOptional.get());
            }
            else {
                orderDishOptional.get().decrementQuantities();
            }
        }

        return "redirect:/app/user/menu";
    }
}
