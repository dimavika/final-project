package com.epam.service;

import com.epam.dao.exception.DaoException;
import com.epam.dao.DaoHelper;
import com.epam.dao.DaoHelperFactory;
import com.epam.dao.album.AlbumDao;
import com.epam.dto.AlbumArtistInfoDto;
import com.epam.entity.Album;
import com.epam.service.exception.ServiceException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AlbumService {

    private DaoHelperFactory daoHelperFactory;

    public AlbumService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void addAlbum(String title, Long artistId, List<Long> audioIds, AudioService audioService)
            throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            try {
                daoHelper.startTransaction();
                AlbumDao albumDao = daoHelper.createAlbumDao();
                Album album = new Album(0L, title, artistId);
                albumDao.save(album);
                Optional<Album> optionalAlbum = albumDao.findAlbumByTitle(title);
                if (optionalAlbum.isPresent()) {
                    audioService.updateAudioByAlbumId(audioIds, optionalAlbum.get().getId(), daoHelper);
                }
            } catch (DaoException e) {
                daoHelper.rollback();
                throw e;
            }
            daoHelper.commit();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<AlbumArtistInfoDto> getAllJoinArtist() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDao dao = daoHelper.createAlbumDao();
            return dao.findAllJoinArtist();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
