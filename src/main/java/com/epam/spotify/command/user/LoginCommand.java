package com.epam.spotify.command.user;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.entity.User;
import com.epam.spotify.service.exception.ServiceException;
import com.epam.spotify.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {

    private final static String MAIN_PAGE = "controller?command=main";
    private final static String LOGIN_PAGE = "controller?command=loginPage";
    private UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> user = service.login(login, password);
        HttpSession session = request.getSession();
        if (user.isPresent()){
            if (!user.get().getIsBlocked()) {
                session.setAttribute("user", user.get());
                return CommandResult.redirect(MAIN_PAGE);
            } else {
                request.setAttribute("errorMessage", "Your account is blocked");
                return CommandResult.forward(LOGIN_PAGE);
            }
        } else {
            request.setAttribute("errorMessage", "Incorrect login or password");
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
