package com.epam.spotify.rowMapper.dto;

import com.epam.spotify.dto.AlbumArtistInfoDto;
import com.epam.spotify.entity.Album;
import com.epam.spotify.rowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumArtistInfoDtoRowMapper implements RowMapper<AlbumArtistInfoDto> {

    @Override
    public AlbumArtistInfoDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Album.ID);
        String title = resultSet.getString(Album.TITLE);
        String artistName = resultSet.getString(AlbumArtistInfoDto.ARTIST_NAME);
        Long artistId = resultSet.getLong(Album.ARTIST_ID);
        return new AlbumArtistInfoDto(id, title, artistName, artistId);
    }
}
