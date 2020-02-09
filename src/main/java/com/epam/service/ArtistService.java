package com.epam.service;

import com.epam.dao.exception.DaoException;
import com.epam.dao.DaoHelper;
import com.epam.dao.DaoHelperFactory;
import com.epam.dao.artist.ArtistDao;
import com.epam.entity.Artist;
import com.epam.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ArtistService {

    private DaoHelperFactory daoHelperFactory;

    public ArtistService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Artist> getAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDao dao = daoHelper.createArtistDao();
            return dao.findAll();
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public void addArtist(String name) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDao dao = daoHelper.createArtistDao();
            Artist artist = new Artist(0L, name);
            dao.save(artist);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Artist> getById(Long artistId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            ArtistDao dao = daoHelper.createArtistDao();
            return dao.findById(artistId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
