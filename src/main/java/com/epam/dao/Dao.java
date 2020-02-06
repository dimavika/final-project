package com.epam.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(Long id) throws DaoException;

    List<T> findAll() throws DaoException;

    void save(T item) throws DaoException;

    void removeById(Long id) throws DaoException;
}
