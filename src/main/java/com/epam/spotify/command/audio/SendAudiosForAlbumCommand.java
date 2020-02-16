package com.epam.spotify.command.audio;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.dto.AudioArtistInfoDto;
import com.epam.spotify.service.AudioService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendAudiosForAlbumCommand implements Command {

    private AudioService service;

    public SendAudiosForAlbumCommand(AudioService service) {
        this.service = service;
    }

    private final static String ALBUM_PAGE = "controller?command=showAlbum";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long albumId = Long.parseLong(request.getParameter("albumId"));
        Long artist = Long.parseLong(request.getParameter("artist"));
        List<AudioArtistInfoDto> audios = service.getAudiosByAlbumId(albumId);
        request.setAttribute("audios", audios);
        request.setAttribute("albumId", albumId);
        request.setAttribute("artist", artist);
        return CommandResult.forward(ALBUM_PAGE);
    }
}
