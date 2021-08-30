package com.github.uinet.controller.command;

import com.github.uinet.dao.DishDAO;
import com.github.uinet.model.OrderDish;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddToCartCommand implements Command{
    private final Logger logger = LogManager.getLogger(AddToCartCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        long dishId = Long.parseLong(request.getParameter("dishId"));

        HttpSession session = request.getSession();

        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");
        if(orderDishList == null){
            orderDishList = new ArrayList<>();
            session.setAttribute("orderDishes", orderDishList);
        }

        Optional<OrderDish> orderDish = orderDishList.stream().filter(orderD-> orderD.getDishId() == dishId).findFirst();

        if(orderDish.isPresent()){
            orderDish.get().incrementQuantities();
        } else {
            orderDishList.add(OrderDish.builder()
                    .dishId(dishId)
                    .quantities(1)
                    .build());
        }

        logger.info("Dish: " + dishId + " was added to cart");

        return "redirect:/app/user/menu";
    }
}
