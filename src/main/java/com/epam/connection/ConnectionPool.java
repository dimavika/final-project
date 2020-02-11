package com.epam.connection;

import com.epam.connection.exception.ConnectionException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionsInUse;
    private static ConnectionPool pool;

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final int NUM_OF_CONNECTIONS = 50;
    private static final Semaphore SEMAPHORE = new Semaphore(NUM_OF_CONNECTIONS);

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    public ConnectionPool(Queue<ProxyConnection> availableConnections, Queue<ProxyConnection> connectionsInUse) {
        this.availableConnections = availableConnections;
        this.connectionsInUse = connectionsInUse;
    }

    public static ConnectionPool getInstance() {
        if (pool == null) {
            LOCK.tryLock();
            try {
                pool = new ConnectionPool(new ArrayDeque<>(), new ArrayDeque<>());
                createConnections(pool);
            } catch (SQLException e) {
                throw new ConnectionException(e); //TODO thr
            } finally {
                LOCK.unlock();
            }
        }
        return pool;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        LOCK.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
                SEMAPHORE.release();
            }
        } finally {
            LOCK.unlock();
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection;
        try {
            SEMAPHORE.acquire();
            LOCK.lock();
            connection = availableConnections.poll();
            connectionsInUse.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionException(e);
        } finally {
            LOCK.unlock();
        }
        return connection;
    }

    private static void createConnections(ConnectionPool pool) throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        int poolSize = Integer.parseInt(resourceBundle.getString("db.poolsize"));
        LOCK.lock();
        try {
            for (int i = 0; i < poolSize; i++) {
                ProxyConnection connection = ConnectionFactory.create(pool);
                pool.availableConnections.offer(connection);
            }
        } finally {
            LOCK.unlock();
        }
    }

    public void shutdown() {
        try {
            for (ProxyConnection connection : availableConnections) {
                connection.close();
            }
            for (ProxyConnection connection : connectionsInUse) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new ConnectionException(e);
        }
    }
}
