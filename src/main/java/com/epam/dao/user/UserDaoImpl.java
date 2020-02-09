package com.epam.dao.user;

import com.epam.dao.AbstractDao;
import com.epam.dao.exception.DaoException;
import com.epam.entity.User;
import com.epam.rowMapper.UserRowMapper;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = MD5(?)";
    private static final String UPDATE_IS_BLOCKED_BY_TRUE = "UPDATE users SET is_blocked = true WHERE id = ?";
    private static final String UPDATE_IS_BLOCKED_BY_FALSE = "UPDATE users SET is_blocked = false WHERE id = ?";
    private static final String REGISTER_BY_LOGIN_AND_PASSWORD = "INSERT users(id, name, login, password) " +
            "VALUES (?, ?, ?, MD5(?))";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    public void updateIsBlockedByTrue(Long id) throws DaoException {
        executeUpdate(UPDATE_IS_BLOCKED_BY_TRUE, id);
    }

    @Override
    public void updateIsBlockedByFalse(Long id) throws DaoException {
        executeUpdate(UPDATE_IS_BLOCKED_BY_FALSE, id);
    }

    @Override
    public String getTableName() {
        return User.TABLE;
    }

    @Override
    public void save(User item) throws DaoException {
//        executeUpdate(REGISTER_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), item.getId(), item.getName(),
//                item.getLogin(), item.getPassword());
    }
}
