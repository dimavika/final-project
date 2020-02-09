package com.epam.dao.album;

import com.epam.dao.AbstractDao;
import com.epam.dao.exception.DaoException;
import com.epam.dto.AlbumArtistInfoDto;
import com.epam.entity.Album;
import com.epam.rowMapper.dto.AlbumArtistInfoDtoRowMapper;
import com.epam.rowMapper.AlbumRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AlbumDaoImpl extends AbstractDao<Album> implements AlbumDao {

    private static final String ADD_ALBUM = "INSERT albums (title, artist_id, price) VALUES (?,?,?)";
    private static final String FIND_ALBUM_BY_TITLE = "SELECT * FROM albums WHERE title = ?";
    private static final String FIND_ALL_JOIN_ARTIST = "SELECT * FROM Albums " +
            "inner join Artist on Albums.artist_id = Artist.id";

    public AlbumDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return Album.TABLE;
    }

    @Override
    public void save(Album item) throws DaoException {
        executeUpdate(ADD_ALBUM, item.getTitle(), item.getArtistId(), item.getPrice());
    }

    @Override
    public Optional<Album> findAlbumByTitle(String title) throws DaoException {
        return executeForSingleResult(FIND_ALBUM_BY_TITLE, new AlbumRowMapper(), title);
    }

    @Override
    public List<AlbumArtistInfoDto> findAllJoinArtist() throws DaoException {
        return executeJoinQuery(FIND_ALL_JOIN_ARTIST, new AlbumArtistInfoDtoRowMapper());
    }
}
