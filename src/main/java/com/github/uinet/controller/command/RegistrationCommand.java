package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegistrationCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        final UserDAOImp userDao = DAOFactory.getInstance().createUserDao();
        Optional<User> userOptional = userDao.getUserByUsername(username);
        System.out.println(userOptional.isPresent());

        if(!userOptional.isPresent()){
            System.out.println("Create");
            userDao.create(User.builder()
                    .role(UserRole.CLIENT)
                    .username(username)
                    .name(name)
                    .password(password)
                    .money(0.0)
                    .build());
        }

        //Todo Registration Error
        return "/registration.jsp";
    }
}
