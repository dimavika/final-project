package com.epam.spotify.command.album;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.AlbumService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAlbumAudioCommand implements Command {

    private AlbumService service;

    private final static String ALBUMS_PAGE = "controller?command=sendAlbums";

    public DeleteAlbumAudioCommand(AlbumService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioId = Long.parseLong(request.getParameter("audioId"));
        service.deleteAudioFromAlbum(audioId);
        return CommandResult.redirect(ALBUMS_PAGE);
    }
}
