package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.DishDAOImp;
import com.github.uinet.model.Dish;
import com.github.uinet.model.DishCategory;

import java.util.List;

public class DishService {
    private final DishDAOImp dishDAOImp = DAOFactory.getInstance().createDishDao();

    public Dish findDishById(long id){
        return dishDAOImp.findById(id);
    }

    public List<Dish> findAllByCategory(DishCategory dishCategory, int page, int recordsPerPage){
        return dishDAOImp.findAllByCategory(dishCategory, page, recordsPerPage);
    }

    public List<Dish> findAllDish(int page, int recordsPerPage ){
        return dishDAOImp.findAll(page, recordsPerPage);
    }

    public int getNumbersOfRows(){
        return dishDAOImp.getNumberOfRows();
    }
}
