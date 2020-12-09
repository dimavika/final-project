package com.epam.spotify.service;

import com.epam.spotify.dao.DaoHelper;
import com.epam.spotify.dao.DaoHelperFactory;
import com.epam.spotify.dao.album.AlbumDao;
import com.epam.spotify.dao.audio.AudioDao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.AlbumArtistInfoDto;
import com.epam.spotify.entity.Album;
import com.epam.spotify.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class AlbumService {

    private DaoHelperFactory daoHelperFactory;

    public AlbumService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void addAlbum(String title, Long artistId, List<Long> audioIds)
            throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            try {
                daoHelper.startTransaction();
                AlbumDao albumDao = daoHelper.createAlbumDao();
                Album album = new Album(0L, title, artistId);
                albumDao.save(album);
                Optional<Album> optionalAlbum = albumDao.findAlbumByTitle(title);
//                AudioDao audioDao = daoHelper.createAudioDao();
                if (optionalAlbum.isPresent()) {
//                    audioService.updateAudioByAlbumId(audioIds, optionalAlbum.get().getId(), daoHelper);
//                    audioDao.updateAlbumId(audioIds, optionalAlbum.get().getId());
                    for (Long audioId : audioIds) {
                        albumDao.saveAlbumAudio(optionalAlbum.get().getId(), audioId);
                    }
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

    public void deleteAlbum(Long albumId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            try {
                daoHelper.startTransaction();
                AlbumDao dao = daoHelper.createAlbumDao();
                dao.removeById(albumId);
                dao.removeAlbumAudioByAlbumId(albumId);
            } catch (DaoException e) {
                daoHelper.rollback();
                throw e;
            }
            daoHelper.commit();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addAudioInAlbum(Long albumId, List<Long> audioIds) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            try {
                daoHelper.startTransaction();
                AlbumDao albumDao = daoHelper.createAlbumDao();
                for (Long audioId : audioIds) {
                    albumDao.saveAlbumAudio(albumId, audioId);
                }
            } catch (DaoException e){
                daoHelper.rollback();
                throw e;
            }
            daoHelper.commit();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteAudioFromAlbum(Long audioId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AudioDao audioDao = daoHelper.createAudioDao();
            audioDao.removeAlbumAudioById(audioId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void changeTitle(Long albumId, String title) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDao albumDao = daoHelper.createAlbumDao();
            albumDao.updateAlbumTitleById(albumId, title);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
