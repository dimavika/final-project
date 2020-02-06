package com.epam.rowMapper;

import com.epam.entity.Audio;
import com.epam.entity.enums.Genre;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AudioRowMapper implements RowMapper<Audio> {

    private static final Logger LOGGER = Logger.getLogger(AudioRowMapper.class);

    @Override
    public Audio map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Audio.ID);
        String title = resultSet.getString(Audio.TITLE);
//        String artistName = resultSet.getString(Audio.ARTIST_NAME);
        Long artistId = resultSet.getLong(Audio.ARTIST_ID);
        Long albumId = resultSet.getLong(Audio.ALBUM_ID);
        BigDecimal price = resultSet.getBigDecimal(Audio.PRICE);
        String genre = resultSet.getString(Audio.GENRE).toUpperCase();
        LOGGER.info(title);
        LOGGER.info(genre);
        return new Audio(id, title, artistId, albumId, price, Genre.valueOf(genre));
    }
}
