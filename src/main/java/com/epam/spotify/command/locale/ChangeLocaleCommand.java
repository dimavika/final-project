package com.epam.spotify.command.locale;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.entity.User;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    private static final String MAIN_PAGE = "controller?command=main";
    private static final String LOGIN_PAGE = "controller?command=loginPage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return CommandResult.forward(MAIN_PAGE);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
