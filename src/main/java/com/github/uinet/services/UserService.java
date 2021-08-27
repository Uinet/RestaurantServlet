package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.exception.UserException;
import com.github.uinet.model.User;

import java.util.Optional;

public class UserService {
    private final UserDAOImp userDAOImp = DAOFactory.getInstance().createUserDao();

    public User loadUserByUsername(String username){
        return userDAOImp.getUserByUsername(username);
    }

    public Optional<User> registerNewUser(User user) throws UserException {
        try {
            return Optional.of(userDAOImp.create(user));
        }catch (Exception exception){
            throw new UserException("User is exist " + exception.getMessage());
        }
    }

    public User loadUserById(long userId) {
        return userDAOImp.findById(userId);
    }
}
