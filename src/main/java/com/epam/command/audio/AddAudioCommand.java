package com.epam.command.audio;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.entity.enums.Genre;
import com.epam.service.ArtistService;
import com.epam.service.AudioService;
import com.epam.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddAudioCommand implements Command {

    private AudioService audioService;
    private final static String AUDIOS_PAGE = "controller?command=audios";

    public AddAudioCommand(AudioService audioService) {
        this.audioService = audioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter("title");
        Long artistId = Long.parseLong(request.getParameter("artist"));
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
        Genre genre = Genre.valueOf((request.getParameter("genre")).toUpperCase());
        audioService.addAudio(title,  artistId, price, genre);
        return CommandResult.redirect(AUDIOS_PAGE);
    }
}
