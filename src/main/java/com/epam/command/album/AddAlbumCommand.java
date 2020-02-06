package com.epam.command.album;

import com.epam.command.Command;
import com.epam.command.CommandResult;
import com.epam.service.AlbumService;
import com.epam.service.ArtistService;
import com.epam.service.AudioService;
import com.epam.service.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddAlbumCommand implements Command {

    private AlbumService albumService;
    private AudioService audioService;

    private static final Logger LOGGER = Logger.getLogger(AddAlbumCommand.class);
    private final static String ALBUMS_PAGE = "controller?command=sendAlbums";

    public AddAlbumCommand(AlbumService albumService, AudioService audioService) {
        this.albumService = albumService;
        this.audioService = audioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        boolean isSingleArtist = true; //TODO проверка на сингл артиста
        String title = request.getParameter("title");
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
        String[] values = request.getParameterValues("albumAudios");
        List<Long> audioIds = new ArrayList<>();
        for (String value : values) {
            audioIds.add(Long.valueOf(value));
        }
        long artistId = Long.valueOf(request.getParameter("artist"));
        albumService.addAlbum(title, artistId, price, audioIds, audioService);
        return CommandResult.redirect(ALBUMS_PAGE);
    }
}
