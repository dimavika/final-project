package com.epam.dao.album;

import com.epam.dao.Dao;
import com.epam.dao.DaoException;
import com.epam.dto.AlbumArtistInfoDto;
import com.epam.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumDao extends Dao<Album> {

    Optional<Album> findAlbumByTitle(String title) throws DaoException;

    List<AlbumArtistInfoDto> findAllJoinArtist() throws DaoException;
}
