package com.github.uinet.controller.command;

import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;
import com.github.uinet.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command{
    private final Logger logger = LogManager.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if( username == null || username.equals("") || password == null || password.equals("")  ){
            return "/login.jsp";
        }

        User user = new UserService().loadUserByUsername(username);

        UserRole role = UserRole.GUEST;

        //TODO password_encryption
        if(user != null && user.getPassword().equals(password)){
            role = user.getRole();
            logger.info("User " + user.getUsername() + " was logged in");
        }

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
