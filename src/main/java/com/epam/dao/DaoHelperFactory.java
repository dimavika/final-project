package com.epam.dao;

import com.epam.connection.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create() throws InterruptedException {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
