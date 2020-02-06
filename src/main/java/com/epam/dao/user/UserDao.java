package com.epam.dao.user;

import com.epam.dao.Dao;
import com.epam.dao.DaoException;
import com.epam.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void updateIsBlockedByTrue(Long id) throws DaoException;

    void updateIsBlockedByFalse(Long id) throws DaoException;
}
