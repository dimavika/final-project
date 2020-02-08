package com.epam.command.artist;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.service.ArtistService;
import com.epam.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddArtistCommand implements Command {

    private ArtistService service;
    private final static String AUDIOS_PAGE = "controller?command=audios";

    public AddArtistCommand(ArtistService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String name = request.getParameter("name");
        service.addArtist(name);
        return CommandResult.redirect(AUDIOS_PAGE);
    }
}
