package com.epam.spotify.command.audio;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.dto.AudioArtistInfoDto;
import com.epam.spotify.service.AudioService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendAudiosForAddAudioInAlbumCommand implements Command {

    private AudioService service;

    private final static String REQUEST = "controller?command=showAddAudioInAlbum";

    public SendAudiosForAddAudioInAlbumCommand(AudioService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long artistId = Long.parseLong(request.getParameter("artist"));
        Long albumId = Long.parseLong(request.getParameter("albumId"));
//        List<Audio> audios = service.getAudiosByArtistId(artistId);
        List<AudioArtistInfoDto> audios = service.getAudiosJoinArtistByArtistId(artistId);
        request.setAttribute("audios", audios);
        request.setAttribute("artistId", artistId);
        request.setAttribute("albumId", albumId);
        return CommandResult.forward(REQUEST);
    }
}
