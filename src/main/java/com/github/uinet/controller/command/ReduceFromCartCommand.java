package com.github.uinet.controller.command;

import com.github.uinet.model.OrderDish;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ReduceFromCartCommand implements Command{
    private final Logger logger = LogManager.getLogger(ReduceFromCartCommand.class);

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
                logger.info("Dish number " + dishId + " has been remove from the cart");
            }
            else {
                orderDishOptional.get().decrementQuantities();
                logger.info("The number of items in the cart has been reduced");
            }
        }

        return "redirect:/app/user/menu";
    }
}
