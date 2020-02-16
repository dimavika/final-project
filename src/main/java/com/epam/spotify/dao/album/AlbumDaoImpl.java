package com.epam.spotify.dao.album;

import com.epam.spotify.dao.AbstractDao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.AlbumArtistInfoDto;
import com.epam.spotify.entity.Album;
import com.epam.spotify.rowMapper.dto.AlbumArtistInfoDtoRowMapper;
import com.epam.spotify.rowMapper.AlbumRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AlbumDaoImpl extends AbstractDao<Album> implements AlbumDao {

    private static final String ADD_ALBUM = "INSERT albums (title, artist_id) VALUES (?,?)";
    private static final String FIND_ALBUM_BY_TITLE = "SELECT * FROM albums WHERE title = ?";
    private static final String FIND_ALL_JOIN_ARTIST = "SELECT * FROM Albums " +
            "inner join Artist on Albums.artist_id = Artist.id";
    private static final String ADD_ALBUM_AUDIO = "INSERT album_audio (album_id, audio_id) VALUES (?,?)";
    private static final String DELETE_ALBUM_AUDIO_BY_ALBUM_ID = "DELETE FROM album_audio WHERE album_id = ?";
    private static final String UPDATE_ALBUM_TITLE = "UPDATE albums SET title = ? WHERE id = ?";

    public AlbumDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return Album.TABLE;
    }

    @Override
    public void save(Album item) throws DaoException {
        executeUpdate(ADD_ALBUM, item.getTitle(), item.getArtistId());
    }

    @Override
    public Optional<Album> findAlbumByTitle(String title) throws DaoException {
        return executeForSingleResult(FIND_ALBUM_BY_TITLE, new AlbumRowMapper(), title);
    }

    @Override
    public List<AlbumArtistInfoDto> findAllJoinArtist() throws DaoException {
        return executeJoinQuery(FIND_ALL_JOIN_ARTIST, new AlbumArtistInfoDtoRowMapper());
    }

    @Override
    public void saveAlbumAudio(Long albumId, Long audioId) throws DaoException {
        executeUpdate(ADD_ALBUM_AUDIO, albumId, audioId);
    }

    @Override
    public void removeAlbumAudioByAlbumId(Long albumId) throws DaoException {
        executeUpdate(DELETE_ALBUM_AUDIO_BY_ALBUM_ID, albumId);
    }

    @Override
    public void updateAlbumTitleById(Long albumId, String title) throws DaoException {
        executeUpdate(UPDATE_ALBUM_TITLE, title, albumId);
    }
}
