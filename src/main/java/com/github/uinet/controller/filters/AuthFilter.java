package com.github.uinet.controller.filters;

import com.github.uinet.dao.DAOFactory;
import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

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

        filterChain.doFilter(req,res);
    }

    @Override
    public void destroy() {

    }
}
