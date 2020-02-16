package com.epam.spotify.command.album;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.dto.AlbumArtistInfoDto;
import com.epam.spotify.service.AlbumService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendAlbumsCommand implements Command {

    private AlbumService service;

    private final static String ALBUMS_PAGE = "controller?command=showAlbums";

    public SendAlbumsCommand(AlbumService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<AlbumArtistInfoDto> albums = service.getAllJoinArtist();
        request.setAttribute("albums", albums);
        return CommandResult.forward(ALBUMS_PAGE);
    }
}
