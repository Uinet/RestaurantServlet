package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;
import com.github.uinet.services.UserService;

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

        UserService userService = new UserService();

        if(!userOptional.isPresent()){
            try {
                userService.registerNewUser(User.builder()
                        .role(UserRole.CLIENT)
                        .username(username)
                        .name(name)
                        .password(password)
                        .money(0.0)
                        .build());
            } catch (Exception e) {
                request.setAttribute("UserIsExist", true);
                e.printStackTrace();
            }

        }

        //Todo Registration Error
        return "/registration.jsp";
    }
}
