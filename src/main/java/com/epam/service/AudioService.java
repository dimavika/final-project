package com.epam.service;

import com.epam.dao.DaoException;
import com.epam.dao.DaoHelper;
import com.epam.dao.DaoHelperFactory;
import com.epam.dao.audio.AudioDao;
import com.epam.dto.AudioArtistInfoDto;
import com.epam.entity.Audio;
import com.epam.entity.enums.Genre;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class AudioService {

    private DaoHelperFactory daoHelperFactory;

    public AudioService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

//    public List<Audio> searchAudiosByTitle(String input) throws ServiceException {
//        try (DaoHelper daoHelper = daoHelperFactory.create()) {
//            //daoHelper.startTransaction();
//            AudioDao audioDao = daoHelper.createAudioDao();
//            return audioDao.findAudiosByTitle(input);
//        } catch (DaoException | InterruptedException e) {
//            throw new ServiceException(e);
//        }
//    }

    public void addAudio(String name, Long artistId, BigDecimal price, Genre genre)
            throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            AudioDao dao = daoHelper.createAudioDao();
            Audio audio = new Audio(0L, name, artistId, null, price, genre);
            dao.save(audio);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }

    public void updateAudioByAlbumId(List<Long> audioIds, Long albumId, DaoHelper daoHelper) throws ServiceException {
        AudioDao dao = daoHelper.createAudioDao();
        try {
            dao.updateAlbumId(audioIds, albumId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

//    public List<Audio> searchAudiosByArtist(String input) throws ServiceException {
//        try (DaoHelper daoHelper = daoHelperFactory.create()) {
//            //daoHelper.startTransaction();
//            AudioDao audioDao = daoHelper.createAudioDao();
//            ArtistDao artistDao = daoHelper.createArtistDao();
//            return audioDao.findAudiosByArtist(input, artistDao);
//        } catch (DaoException | InterruptedException e) {
//            throw new ServiceException(e);
//        }
//    }

    public List<Audio> searchAudiosByArtistId(Long artistId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            AudioDao audioDao = daoHelper.createAudioDao();
            return audioDao.findAudiosByArtistId(artistId);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }

//    public Set<Audio> searchAudios(String input) throws ServiceException {
//        try (DaoHelper daoHelper = daoHelperFactory.create()) {
//            //daoHelper.startTransaction();
//            AudioDao audioDao = daoHelper.createAudioDao();
//            ArtistDao artistDao = daoHelper.createArtistDao();
//            return audioDao.findAudiosBySearch(input, artistDao);
//        } catch (DaoException | InterruptedException e) {
//            throw new ServiceException(e);
//        }
//    }

    public Set<AudioArtistInfoDto> searchAudiosJoinArtist(String input) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            AudioDao audioDao = daoHelper.createAudioDao();
            return audioDao.findAudiosJoinArtistBySearch(input);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }

    public List<AudioArtistInfoDto> searchAudiosByAlbumId(Long albumId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            AudioDao audioDao = daoHelper.createAudioDao();
            return audioDao.findAudiosJoinArtistByAlbumId(albumId);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteAudio(long audioId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            //daoHelper.startTransaction();
            AudioDao audioDao = daoHelper.createAudioDao();
            audioDao.removeById(audioId);
        } catch (DaoException | InterruptedException e) {
            throw new ServiceException(e);
        }
    }
}
