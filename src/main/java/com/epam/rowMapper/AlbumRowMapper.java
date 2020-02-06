package com.epam.rowMapper;

import com.epam.entity.Album;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumRowMapper implements RowMapper<Album> {

    @Override
    public Album map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Album.ID);
        String title = resultSet.getString(Album.TITLE);
        Long artistId = resultSet.getLong(Album.ARTIST_ID);
        BigDecimal price = resultSet.getBigDecimal(Album.PRICE);
        return new Album(id, title, artistId, price);
    }
}
