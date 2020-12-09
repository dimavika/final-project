package com.epam.spotify.command.audio;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.service.AudioService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAudioCommand implements Command {

    private AudioService service;

    private static final String AUDIOS_PAGE = "controller?command=audios";

    public DeleteAudioCommand(AudioService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioId = Long.valueOf(request.getParameter("audioId"));
        service.deleteAudio(audioId);
        return CommandResult.redirect(AUDIOS_PAGE);
    }
}
