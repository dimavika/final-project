package com.epam.rowMapper.dto;

import com.epam.dto.AlbumArtistInfoDto;
import com.epam.entity.Album;
import com.epam.rowMapper.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumArtistInfoDtoRowMapper implements RowMapper<AlbumArtistInfoDto> {

    @Override
    public AlbumArtistInfoDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Album.ID);
        String title = resultSet.getString(Album.TITLE);
        String artistName = resultSet.getString(AlbumArtistInfoDto.ARTIST_NAME);
        BigDecimal price = resultSet.getBigDecimal(Album.PRICE);
        return new AlbumArtistInfoDto(id, title, artistName, price);
    }
}
