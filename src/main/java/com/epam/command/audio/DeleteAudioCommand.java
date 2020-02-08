package com.epam.command.audio;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.service.AudioService;
import com.epam.service.exception.ServiceException;

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
