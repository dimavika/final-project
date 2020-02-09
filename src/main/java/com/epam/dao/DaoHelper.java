package com.epam.dao;

import com.epam.connection.ConnectionPool;
import com.epam.connection.ProxyConnection;
import com.epam.dao.album.AlbumDaoImpl;
import com.epam.dao.artist.ArtistDaoImpl;
import com.epam.dao.audio.AudioDaoImpl;
import com.epam.dao.exception.DaoException;
import com.epam.dao.exception.DaoRuntimeException;
import com.epam.dao.order.OrderDaoImpl;
import com.epam.dao.review.ReviewDaoImpl;
import com.epam.dao.user.UserDaoImpl;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private ProxyConnection connection;

    public DaoHelper(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }

    public UserDaoImpl createUserDao(){
        return new UserDaoImpl(connection);
    }

    public AudioDaoImpl createAudioDao(){
        return new AudioDaoImpl(connection);
    }

    public ArtistDaoImpl createArtistDao(){
        return new ArtistDaoImpl(connection);
    }

    public AlbumDaoImpl createAlbumDao(){
        return new AlbumDaoImpl(connection);
    }

    public ReviewDaoImpl createReviewDao(){
        return new ReviewDaoImpl(connection);
    }

    public OrderDaoImpl createOrderDao(){
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

    public void endTransaction() throws DaoException {
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
            throw new DaoRuntimeException(e);
        }
        }
}
