package com.epam.spotify.rowMapper.dto;

import com.epam.spotify.dto.AudioArtistInfoDto;
import com.epam.spotify.entity.Audio;
import com.epam.spotify.entity.enums.Genre;
import com.epam.spotify.rowMapper.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AudioArtistInfoDtoRowMapper implements RowMapper<AudioArtistInfoDto> {

    @Override
    public AudioArtistInfoDto map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(AudioArtistInfoDto.AUDIO_ID);
        String title = resultSet.getString(Audio.TITLE);
        String artistName = resultSet.getString(AudioArtistInfoDto.ARTIST_NAME);
        BigDecimal price = resultSet.getBigDecimal(Audio.PRICE);
        String genre = resultSet.getString(Audio.GENRE).toUpperCase();
        return new AudioArtistInfoDto(id, title, artistName, price, Genre.valueOf(genre));
    }
}
