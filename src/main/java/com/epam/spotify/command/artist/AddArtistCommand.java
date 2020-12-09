package com.epam.spotify.command.artist;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.ArtistService;
import com.epam.spotify.service.exception.ServiceException;

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
