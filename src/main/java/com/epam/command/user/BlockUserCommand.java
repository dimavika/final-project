package com.epam.command.user;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.service.ServiceException;
import com.epam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserCommand implements Command {

    private UserService service;

    private static final String SHOW_USERS_COMMAND = "controller?command=showUsers";

    public BlockUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long id = Long.valueOf(request.getParameter("userId"));
        service.blockUser(id);
        return CommandResult.redirect(SHOW_USERS_COMMAND);
    }
}
