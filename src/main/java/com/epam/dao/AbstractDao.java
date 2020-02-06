package com.epam.dao;

import com.epam.dto.Dto;
import com.epam.rowMapper.RowMapper;
import com.epam.entity.Identifiable;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    private Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {

        try(PreparedStatement statement = createStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info(resultSet);
            List<T> entities = new ArrayList<>();
            while (resultSet.next()){
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            LOGGER.info(entities.toString());
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected <E extends Dto>List<E> executeJoinQuery(String query, RowMapper<E> mapper, Object... params) throws DaoException {

        try(PreparedStatement statement = createStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            List<E> entities = new ArrayList<>();
            while (resultSet.next()){
                E entity = mapper.map(resultSet);
                entities.add(entity);
            }
            LOGGER.info(entities.toString());
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected void executeUpdate(String query, Object... params) throws DaoException {

        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        LOGGER.info(params.length + " " + Arrays.toString(params));
        LOGGER.info(statement.toString());
        for (int i = 1; i <= params.length; i++){
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    public List<T> findAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("SELECT * FROM " + table, mapper);
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeForSingleResult("SELECT * FROM " + table + " WHERE id = ?", mapper, id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        String table = getTableName();
        executeUpdate("DELETE FROM " + table + " WHERE id = ?", id);
    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        List<T> items = executeQuery(query, mapper, params);
        if (items.size() == 1){
            return Optional.of(items.get(0));
        } else if (items.size() > 1){
            throw new DaoException("More then one record found");
        } else {
            return Optional.empty();
        }
    }

    public abstract String getTableName();
}
