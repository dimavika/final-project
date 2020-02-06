package com.epam.dao.artist;

import com.epam.dao.Dao;
import com.epam.dao.DaoException;
import com.epam.entity.Artist;

import java.util.List;

public interface ArtistDao extends Dao<Artist> {

    List<Artist> findArtistByName(String input) throws DaoException;
}
