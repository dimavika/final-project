package com.epam.controller;

import com.epam.command.Command;
import com.epam.command.CommandFactory;
import com.epam.command.CommandResult;
import com.epam.connection.ConnectionPool;
import com.epam.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    public void destroy() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.shutdown();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            java.io.IOException {
        String page;
        boolean isRedirect = false;
        try {
            Command command = CommandFactory.create(request.getParameter("command"));
            CommandResult commandResult = command.execute(request, response);
            page = commandResult.getPage();
            isRedirect = commandResult.isRedirect();
        } catch (ServiceException e) {
            LOGGER.error(e);
            page = "/error.jsp";
        }
        dispatch(request, response, page, isRedirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
            processRequest(request, response);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page, boolean isRedirect)
            throws  javax.servlet.ServletException, java.io.IOException {
        if (!isRedirect) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(page);
        }
    }
}

