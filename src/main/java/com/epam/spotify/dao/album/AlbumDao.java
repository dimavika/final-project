package com.epam.spotify.dao.album;

import com.epam.spotify.dao.Dao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.AlbumArtistInfoDto;
import com.epam.spotify.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumDao extends Dao<Album> {

    Optional<Album> findAlbumByTitle(String title) throws DaoException;

    List<AlbumArtistInfoDto> findAllJoinArtist() throws DaoException;

    void saveAlbumAudio(Long albumId, Long audioId) throws DaoException;

    void removeAlbumAudioByAlbumId(Long albumId) throws DaoException;

    void updateAlbumTitleById(Long albumId, String title) throws DaoException;
}
