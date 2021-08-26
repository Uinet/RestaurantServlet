package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.DishDAOImp;
import com.github.uinet.model.Dish;
import com.github.uinet.model.DishCategory;

import java.util.List;
import java.util.Optional;

public class DishService {
    private final DishDAOImp dishDAOImp = DAOFactory.getInstance().createDishDao();

    public Optional<Dish> findDishById(Long id){
        return dishDAOImp.findById(id);
    }

    public List<Dish> findAllByCategory(DishCategory dishCategory){
        return dishDAOImp.findAllByCategory(dishCategory);
    }

    public List<Dish> findAllDish(){
        return dishDAOImp.findAll();
    }
}
