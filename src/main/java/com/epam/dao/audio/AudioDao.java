package com.epam.dao.audio;

import com.epam.dao.Dao;
import com.epam.dao.exception.DaoException;
import com.epam.dto.AudioArtistInfoDto;
import com.epam.entity.Audio;

import java.util.List;

public interface AudioDao extends Dao<Audio> {

//    List<Audio> findAudiosByTitle(String input) throws DaoException;
//
//    List<Audio> findAudiosByArtist(String input, ArtistDao artistDao) throws DaoException;
//
//    Set<Audio> findAudiosBySearch(String input, ArtistDao artistDao) throws DaoException;

    List<Audio> findAudiosByArtistId(long artistId) throws DaoException;

    void updateAlbumId(List<Long> audioIds, Long albumId) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByAlbumId(long albumId) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByTitle(String input) throws DaoException;

    List<AudioArtistInfoDto> findAudiosJoinArtistByArtist(String input) throws DaoException;
}
