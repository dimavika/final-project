package com.epam.spotify.dao.user;

import com.epam.spotify.dao.Dao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void updateIsBlockedByTrue(Long id) throws DaoException;

    void updateIsBlockedByFalse(Long id) throws DaoException;
}
