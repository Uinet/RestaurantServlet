package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.DishDAOImp;
import com.github.uinet.exception.DAOException;
import com.github.uinet.model.Dish;
import com.github.uinet.model.DishCategory;

import java.util.Arrays;
import java.util.List;

public class DishService {
    private final DishDAOImp dishDAOImp;

    public DishService(){
        dishDAOImp = DAOFactory.getInstance().createDishDao();
    }

    public DishService(DishDAOImp dishDAOImp){
        this.dishDAOImp = dishDAOImp;
    }

    public Dish findDishById(long id) throws DAOException {
        return dishDAOImp.findById(id);
    }

    public List<Dish> findAllByCategory(DishCategory dishCategory, int page, int recordsPerPage) throws DAOException {
        return dishDAOImp.findAllByCategory(dishCategory, page, recordsPerPage);
    }

    public List<Dish> findAllDish(int page, int recordsPerPage ) throws DAOException {
        return dishDAOImp.findAll(page, recordsPerPage);
    }

    public int getNumbersOfRows() throws DAOException {
        return dishDAOImp.getNumberOfRows();
    }

    public List<Dish> getSortedDishes(String sortField, String sortDirection) throws DAOException {
        List<String> allowedSortableColumns = Arrays.asList(new String[]{"name", "price", "category"});
        if(allowedSortableColumns.contains(sortField)){
            if(sortDirection.equals("DESC")){
                return dishDAOImp.getSortedDishes(sortField, sortDirection);
            }
            else {
                return dishDAOImp.getSortedDishes(sortField, "ASC");
            }
        }
        else {
            throw new RuntimeException("Cannot sort by: " + sortField);
        }
    }
}
