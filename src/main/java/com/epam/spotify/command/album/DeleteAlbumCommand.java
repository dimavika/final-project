package com.epam.spotify.command.album;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.AlbumService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAlbumCommand implements Command {

    private AlbumService service;

    private final static String ALBUMS_PAGE = "controller?command=sendAlbums";

    public DeleteAlbumCommand(AlbumService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long albumId = Long.valueOf(request.getParameter("albumId"));
        service.deleteAlbum(albumId);
        return CommandResult.redirect(ALBUMS_PAGE);
    }
}
