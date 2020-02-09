package com.epam.command.user;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private final static String LOGIN_PAGE = "controller?command=loginPage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return CommandResult.redirect(LOGIN_PAGE);
    }
}
