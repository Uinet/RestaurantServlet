package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.model.User;

import java.util.Optional;

public class UserService {
    private final UserDAOImp userDAOImp = DAOFactory.getInstance().createUserDao();

    public User loadUserByUsername(String username) throws Exception {
        return userDAOImp.getUserByUsername(username).orElseThrow(()->{
            return new Exception("Email: " + username + "not found");
        });
    }

    public Optional<User> registerNewUser(User user) throws Exception {
        try {
            return Optional.of(userDAOImp.create(user));
        }catch (Exception exception){
            throw new Exception("User is exist " + exception.getMessage());
        }
    }
}
