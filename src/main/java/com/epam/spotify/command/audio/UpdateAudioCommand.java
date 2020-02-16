package com.epam.spotify.command.audio;

import com.epam.spotify.command.Command;
import com.epam.spotify.command.CommandResult;
import com.epam.spotify.entity.enums.Genre;
import com.epam.spotify.service.AudioService;
import com.epam.spotify.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class UpdateAudioCommand implements Command {

    private AudioService service;

    private final static String AUDIOS_PAGE = "controller?command=audios";

    public UpdateAudioCommand(AudioService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter("title");
        Long artistId = Long.parseLong(request.getParameter("artist"));
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
        Genre genre = Genre.valueOf((request.getParameter("genre")).toUpperCase());
        Long audioId = Long.parseLong(request.getParameter("audioId"));
        service.updateAudio(title, artistId, price, genre, audioId);
        return CommandResult.redirect(AUDIOS_PAGE);
    }
}
