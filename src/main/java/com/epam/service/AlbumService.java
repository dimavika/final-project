package com.epam.service;

import com.epam.dao.DaoException;
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

    public void addAlbum(String title, Long artistId, BigDecimal price, List<Long> audioIds, AudioService audioService)
            throws ServiceException { //TODO ask about SQLException
//        DaoHelper daoHelper = null;
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            daoHelper.startTransaction();
            AlbumDao albumDao = daoHelper.createAlbumDao();
            Album album = new Album(0L, title, artistId, price);
            albumDao.save(album);
            Optional<Album> optionalAlbum = albumDao.findAlbumByTitle(title);
            if (optionalAlbum.isPresent()){
                audioService.updateAudioByAlbumId(audioIds, optionalAlbum.get().getId(), daoHelper);
            }
            daoHelper.commit();
        } catch (DaoException | SQLException e) {
//            daoHelper.rollback();
            throw new ServiceException(e);
        }
    }

    public List<AlbumArtistInfoDto> getAllJoinArtist() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            AlbumDao dao = daoHelper.createAlbumDao();
            return dao.findAllJoinArtist();
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
