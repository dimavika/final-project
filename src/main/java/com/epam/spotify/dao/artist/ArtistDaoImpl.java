package com.epam.spotify.dao.artist;

import com.epam.spotify.dao.AbstractDao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.entity.Artist;
import com.epam.spotify.rowMapper.ArtistRowMapper;

import java.sql.Connection;
import java.util.List;

public class ArtistDaoImpl extends AbstractDao<Artist> implements ArtistDao {

    private static final String ADD_ARTIST = "INSERT artist (name) VALUES (?)";
    private static final String FIND_ARTIST_BY_NAME = "SELECT * FROM artist WHERE name LIKE '%";

    public ArtistDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return Artist.TABLE;
    }

    @Override
    public void save(Artist item) throws DaoException {
        executeUpdate(ADD_ARTIST, item.getName());
    }

    @Override
    public List<Artist> findArtistByName(String input) throws DaoException {
        return executeQuery(getDynamicStatement(input), new ArtistRowMapper());
    }

    private String getDynamicStatement(String name){
        return FIND_ARTIST_BY_NAME + name + "%'";
    }
}
