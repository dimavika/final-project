package com.epam.spotify.command.user;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.exception.ServiceException;
import com.epam.spotify.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command {

    private UserService service;

    public RegisterCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
//        String showAllAudios = request.getParameter("showAllAudios");
//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        int id = Integer.parseInt(request.getParameter("id"));
//        service.register(showAllAudios, password, name, id);
        return CommandResult.redirect("/showAllAudios.jsp");
    }
}
