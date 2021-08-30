package com.github.uinet.services;

import com.github.uinet.dao.imp.DishDAOImp;
import com.github.uinet.model.Dish;
import com.github.uinet.model.DishCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DishServiceTest {

    @Mock
    private DishDAOImp dishDAOImp;

    private DishService dishService;

    private Dish dish;

    @Before
    public void init(){
        Dish.builder()
                .id(1l)
                .name("TestName")
                .description("TestDescription")
                .category(DishCategory.MAIN)
                .price(BigDecimal.valueOf(10.5))
                .build();
        dishService = new DishService(dishDAOImp);
    }

    @Test
    public void findDishById() {
        when(dishDAOImp.findById(dish.getId())).thenReturn(dish);
        assertEquals(dish, dishService.findDishById(dish.getId()));
    }

    @Test
    public void findAllByCategory() {
    }

    @Test
    public void findAllDish() {
    }

    @Test
    public void getNumbersOfRows() {
        when(dishDAOImp.getNumberOfRows()).thenReturn(10);
        assertEquals(10, dishService.getNumbersOfRows());
    }

    @Test
    public void getSortedDishes() {
    }
}