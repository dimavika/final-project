package com.epam.command.audio;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.dto.AudioArtistInfoDto;
import com.epam.service.AudioService;
import com.epam.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ShowAudiosBySearchCommand implements Command {

    private AudioService audioService;

    private final static String AUDIOS_PAGE = "controller?command=audios";
    private static final Logger LOGGER = Logger.getLogger(ShowAudiosBySearchCommand.class);

    public ShowAudiosBySearchCommand(AudioService audioService) {
        this.audioService = audioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String input = request.getParameter("input");
        Set<AudioArtistInfoDto> audios = audioService.getAudiosJoinArtistBySearchInput(input);
        request.setAttribute("audios", audios);
        return CommandResult.forward(AUDIOS_PAGE);
    }
}
