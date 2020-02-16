package com.epam.spotify.command.album;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.AlbumService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddAudioInAlbumCommand implements Command {

    private AlbumService service;

    private final static String ALBUMS_PAGE = "controller?command=sendAlbums";

    public AddAudioInAlbumCommand(AlbumService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long albumId = Long.valueOf(request.getParameter("albumId"));
        String[] values = request.getParameterValues("albumAudios");
        List<Long> audioIds = new ArrayList<>();
        if (values != null) {
            for (String value : values) {
                audioIds.add(Long.valueOf(value));
            }
        } //TODO thr error
        service.addAudioInAlbum(albumId, audioIds);
        return CommandResult.redirect(ALBUMS_PAGE);
    }
}
