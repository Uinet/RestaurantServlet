package com.github.uinet.dao.imp;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.utils.ConnectionCreator;

public class DAOFactoryImp extends DAOFactory {
    public UserDAOImp createUserDao(){
        return new UserDAOImp(ConnectionCreator.createConnection());
    }
}
