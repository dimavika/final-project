package com.epam.rowMapper;

import com.epam.entity.User;
import com.epam.entity.enums.Role;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    private static final Logger LOGGER = Logger.getLogger(UserRowMapper.class);

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString(User.LOGIN);
        long id = resultSet.getLong(User.ID);
        String role = resultSet.getString(User.ROLE).toUpperCase();
        Boolean isBlocked = resultSet.getBoolean(User.IS_BLOCKED);
        LOGGER.info(login);
        LOGGER.info(role);
        return new User(id, login, Role.valueOf(role), isBlocked);
    }
}
