package com.epam.spotify.command.album;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.AlbumService;
import com.epam.spotify.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddAlbumCommand implements Command {

    private AlbumService albumService;

    private static final Logger LOGGER = Logger.getLogger(AddAlbumCommand.class);
    private final static String ALBUMS_PAGE = "controller?command=sendAlbums";

    public AddAlbumCommand(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter("title");
        String[] values = request.getParameterValues("albumAudios");
        List<Long> audioIds = new ArrayList<>();
        for (String value : values) {
            audioIds.add(Long.valueOf(value));
        }
        long artistId = Long.valueOf(request.getParameter("artist"));
        albumService.addAlbum(title, artistId, audioIds);
        return CommandResult.redirect(ALBUMS_PAGE);
    }
}
