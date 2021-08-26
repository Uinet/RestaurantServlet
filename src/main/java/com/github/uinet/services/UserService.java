package com.github.uinet.services;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.exception.UserException;
import com.github.uinet.model.User;

import java.util.Optional;

public class UserService {
    private final UserDAOImp userDAOImp = DAOFactory.getInstance().createUserDao();

    public User loadUserByUsername(String username) throws Exception {
        return userDAOImp.getUserByUsername(username).orElseThrow(()->{
            return new Exception("Email: " + username + "not found");
        });
    }

    public Optional<User> registerNewUser(User user) throws UserException {
        try {
            return Optional.of(userDAOImp.create(user));
        }catch (Exception exception){
            throw new UserException("User is exist " + exception.getMessage());
        }
    }

    public User loadUserById(Long userId) {
        return userDAOImp.findById(userId);
    }
}
