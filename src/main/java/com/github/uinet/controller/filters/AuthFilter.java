package com.github.uinet.controller.filters;

import com.github.uinet.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        ServletContext context = req.getSession().getServletContext();

        UserRole role = (UserRole) session.getAttribute("role");
        if(role == null){
            role = UserRole.GUEST;
            session.setAttribute("role", role);
        }

        System.out.println("AuthFilter user role:" +role);
        System.out.println("AuthFilter request URI:" + req.getRequestURI());
        if(role.equals(UserRole.GUEST) && (req.getRequestURI().contains("admin") || req.getRequestURI().contains("user"))) {
            res.sendRedirect("/api/");
        }else if(role.equals(UserRole.CLIENT) && req.getRequestURI().contains("admin")) {
            res.sendRedirect("/api/");
        }else if(!role.equals(UserRole.GUEST) && (req.getRequestURI().contains("login")) || req.getRequestURI().contains("registration")){
            res.sendRedirect("/api/");
        }else {
            filterChain.doFilter(req,res);
        }
    }

    @Override
    public void destroy() {

    }
}
