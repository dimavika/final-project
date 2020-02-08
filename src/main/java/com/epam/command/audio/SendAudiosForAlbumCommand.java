package com.epam.command.audio;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.dto.AudioArtistInfoDto;
import com.epam.service.AudioService;
import com.epam.service.exception.ServiceException;

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
        List<AudioArtistInfoDto> audios = service.searchAudiosByAlbumId(albumId);
        request.setAttribute("audios", audios);
        return CommandResult.forward(ALBUM_PAGE);
    }
}
