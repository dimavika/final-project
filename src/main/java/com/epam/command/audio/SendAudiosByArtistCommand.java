package com.epam.command.audio;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.entity.Audio;
import com.epam.service.AudioService;
import com.epam.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SendAudiosByArtistCommand implements Command {

    private AudioService service;

    private static final Logger LOGGER = Logger.getLogger(SendAudiosByArtistCommand.class);
    private final static String ADD_ALBUM_SECOND_PAGE = "controller?command=showAddAlbumSecond";

    public SendAudiosByArtistCommand(AudioService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        long artistId = Long.parseLong(request.getParameter("artist"));
        LOGGER.info(artistId);
        List<Audio> audios = service.searchAudiosByArtistId(artistId);
        request.setAttribute("audios", audios);
        request.setAttribute("artistId", artistId);
        return CommandResult.forward(ADD_ALBUM_SECOND_PAGE);
    }
}
