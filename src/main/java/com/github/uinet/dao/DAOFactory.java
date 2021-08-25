package com.github.uinet.dao;

import com.github.uinet.dao.imp.*;

import static java.util.Objects.isNull;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;

    public abstract UserDAOImp createUserDao();
    public abstract DishDAOImp createDishDao();
    public abstract OrderDAOImp createOrderDao();
    public abstract OrderDishDAOImp createOrderDishDao();

    public static DAOFactory getInstance(){
        if(isNull(daoFactory)){
            synchronized (DAOFactory.class){
                if(isNull(daoFactory)){
                    daoFactory = new DAOFactoryImp();
                }
            }
        }
        return daoFactory;
    }
}
