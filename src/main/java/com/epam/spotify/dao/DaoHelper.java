package com.epam.spotify.dao;

import com.epam.spotify.connection.ConnectionPool;
import com.epam.spotify.connection.ProxyConnection;
import com.epam.spotify.dao.album.AlbumDaoImpl;
import com.epam.spotify.dao.artist.ArtistDaoImpl;
import com.epam.spotify.dao.audio.AudioDaoImpl;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dao.exception.DaoRuntimeException;
import com.epam.spotify.dao.order.OrderDaoImpl;
import com.epam.spotify.dao.review.ReviewDaoImpl;
import com.epam.spotify.dao.user.UserDaoImpl;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;

    private static final Logger LOGGER = Logger.getLogger(DaoHelper.class);

    public DaoHelper(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }

    public UserDaoImpl createUserDao() {
        return new UserDaoImpl(connection);
    }

    public AudioDaoImpl createAudioDao() {
        return new AudioDaoImpl(connection);
    }

    public ArtistDaoImpl createArtistDao() {
        return new ArtistDaoImpl(connection);
    }

    public AlbumDaoImpl createAlbumDao() {
        return new AlbumDaoImpl(connection);
    }

    public ReviewDaoImpl createReviewDao() {
        return new ReviewDaoImpl(connection);
    }

    public OrderDaoImpl createOrderDao() {
        return new OrderDaoImpl(connection);
    }

    @Override
    public void close() {
        connection.returnConnection();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit() throws DaoException {
        try {
            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoRuntimeException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
