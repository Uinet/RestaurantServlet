package com.github.uinet.dao.imp;

import com.github.uinet.dao.DAOFactory;

public class DAOFactoryImp extends DAOFactory {
    public UserDAOImp createUserDao(){
        return new UserDAOImp();
    }
    public DishDAOImp createDishDao(){return new DishDAOImp();}
    public OrderDAOImp createOrderDao(){return new OrderDAOImp();}
    public OrderDishDAOImp createOrderDishDao() {return new OrderDishDAOImp();}
}
