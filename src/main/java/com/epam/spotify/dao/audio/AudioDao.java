package com.epam.spotify.dao.audio;

import com.epam.spotify.dao.Dao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.AudioArtistInfoDto;
import com.epam.spotify.entity.Audio;
import com.epam.spotify.entity.enums.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface AudioDao extends Dao<Audio> {

//    List<Audio> findAudiosByTitle(String input) throws DaoException;
//
//    List<Audio> findAudiosByArtist(String input, ArtistDao artistDao) throws DaoException;
//
//    Set<Audio> findAudiosBySearch(String input, ArtistDao artistDao) throws DaoException;

    List<Audio> findAudiosByArtistId(long artistId) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByAlbumId(long albumId) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByAlbumIdNew(long albumId) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByTitle(String input) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByArtist(String input) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByArtistWithNoAlbum(Long artistId) throws DaoException;

    void removeAlbumAudioById(Long audioId) throws DaoException;

    void updateAudio(String title, Long artistId, BigDecimal price, Genre genre, Long audioId) throws DaoException;
}
