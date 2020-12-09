package com.epam.spotify.command.user;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.exception.ServiceException;
import com.epam.spotify.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockUserCommand implements Command {

    private UserService service;

    private static final String SHOW_USERS_COMMAND = "controller?command=showUsers";

    public UnblockUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long id = Long.valueOf(request.getParameter("userId"));
        service.unblockUser(id);
        return CommandResult.redirect(SHOW_USERS_COMMAND);
    }
}
