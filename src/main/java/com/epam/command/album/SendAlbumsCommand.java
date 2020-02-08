package com.epam.command.album;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.dto.AlbumArtistInfoDto;
import com.epam.service.AlbumService;
import com.epam.service.exception.ServiceException;

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
