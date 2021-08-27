package com.github.uinet.controller.command;

import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;
import com.github.uinet.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        if( username == null || username.equals("") || password == null || password.equals("")  ){
            return "/login.jsp";
        }

        User user = new UserService().loadUserByUsername(username);

        UserRole role = UserRole.GUEST;

        //TODO password_encryption
        if(user != null && user.getPassword().equals(password)){
            role = user.getRole();
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
