package com.github.uinet.controller.command;

import com.github.uinet.model.OrderDish;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RemoveFromCartCommand implements Command{
    private final Logger logger = LogManager.getLogger(RemoveFromCartCommand.class);
    @Override
    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        long dishId = Long.parseLong(request.getParameter("dishId"));

        List<OrderDish> orderDishList = (List<OrderDish>) session.getAttribute("orderDishes");
        orderDishList.removeIf(orderDish -> orderDish.getDishId() == dishId);

        logger.info("Dish number " + dishId + " has been remove from the cart");

        return "redirect:/app/user/menu";
    }
}
