package com.epam.connection;

import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    public static ProxyConnection create(ConnectionPool pool) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        return new ProxyConnection(DriverManager.getConnection(url, user, pass), pool);
    }
}