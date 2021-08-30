package com.github.uinet.controller.command;

import com.github.uinet.model.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    private static final Logger logger = LogManager.getLogger(CommandUtility.class);

    static void setUserRole(HttpServletRequest request,
                            UserRole role, String name) {
        HttpSession session = request.getSession();
        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("username", name);
        session.setAttribute("role", role);

        logger.info("Set to" + name +
                "for the username attribute of the context");

        logger.info("Set to" + role +
                "for the role attribute of the session");
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName){
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(userName::equals)){
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
