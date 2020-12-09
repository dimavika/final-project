package com.epam.spotify.command.audio;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.dto.AudioArtistInfoDto;
import com.epam.spotify.service.AudioService;
import com.epam.spotify.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendAudiosByArtistCommand implements Command {

    private AudioService service;

    private static final Logger LOGGER = Logger.getLogger(SendAudiosByArtistCommand.class);
    private final static String REQUEST = "controller?command=showAddAlbumSecond";

    public SendAudiosByArtistCommand(AudioService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long artistId = Long.parseLong(request.getParameter("artist"));
        LOGGER.info(artistId);
//        List<Audio> audios = service.getAudiosByArtistId(artistId);
        List<AudioArtistInfoDto> audios = service.getAudiosJoinArtistByArtistId(artistId);
        request.setAttribute("audios", audios);
        request.setAttribute("artistId", artistId);
        return CommandResult.forward(REQUEST);
    }
}
