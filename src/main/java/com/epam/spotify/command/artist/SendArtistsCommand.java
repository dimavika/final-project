package com.epam.spotify.command.artist;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.entity.Artist;
import com.epam.spotify.service.ArtistService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendArtistsCommand implements Command {

    private ArtistService service;
    private String page;

    public SendArtistsCommand(ArtistService service, String page) {
        this.service = service;
        this.page = page;
    }

    private final static String ADD_AUDIO_PAGE = "controller?command=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Artist> artists = service.getAll();
        String audioIdStr = request.getParameter("audioId");
        if (audioIdStr != null) {
            request.setAttribute("audioId", Long.parseLong(audioIdStr));
        }
        request.setAttribute("artists", artists);
        return CommandResult.forward(ADD_AUDIO_PAGE + page);
    }
}
