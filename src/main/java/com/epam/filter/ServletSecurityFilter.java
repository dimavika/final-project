package com.epam.filter;

import com.epam.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/controller" }, servletNames = { "Controller" })
public class ServletSecurityFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {
    }
}
