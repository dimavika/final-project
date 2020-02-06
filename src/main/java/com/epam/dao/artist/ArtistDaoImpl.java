package com.epam.dao.artist;

import com.epam.dao.AbstractDao;
import com.epam.dao.DaoException;
import com.epam.entity.Artist;
import com.epam.rowMapper.ArtistRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

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
