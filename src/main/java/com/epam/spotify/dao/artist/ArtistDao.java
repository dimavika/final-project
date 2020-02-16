package com.epam.spotify.dao.artist;

import com.epam.spotify.dao.Dao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.entity.Artist;

import java.util.List;

public interface ArtistDao extends Dao<Artist> {

    List<Artist> findArtistByName(String input) throws DaoException;
}
