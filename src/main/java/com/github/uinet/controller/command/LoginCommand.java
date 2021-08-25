package com.github.uinet.controller.command;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        if( username == null || username.equals("") || password == null || password.equals("")  ){
            return "/login.jsp";
        }

        final UserDAOImp userDao = DAOFactory.getInstance().createUserDao();
        Optional<User> userOptional = userDao.getUserByUsername(username);

        UserRole role = UserRole.GUEST;

        //TODO password_encryption

        if(userOptional.isPresent()){
            if(userOptional.get().getPassword().equals(password)){
                role = userOptional.get().getRole();
            }
        }

        System.out.println(username);
        System.out.println(password);
        System.out.println(role);

        CommandUtility.setUserRole(request, role, username);

        if(role.equals(UserRole.MANAGER)){
            return "redirect:/app/admin/orders";
        } else if(role.equals(UserRole.CLIENT)){
            return "redirect:/app/user/menu";
        } else {
            return "/login.jsp";
        }
    }
}
