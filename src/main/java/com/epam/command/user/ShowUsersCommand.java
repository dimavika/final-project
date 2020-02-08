package com.epam.command.user;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.entity.User;
import com.epam.service.exception.ServiceException;
import com.epam.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUsersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(ShowUsersCommand.class);

    private UserService service;

    private final static String USERS_PAGE = "controller?command=users";

    public ShowUsersCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = service.getAll();
        request.setAttribute("users", users);
        return CommandResult.forward(USERS_PAGE);
    }
}
