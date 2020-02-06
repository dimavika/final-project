package com.epam.connection;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
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

    public static ConnectionPool getInstance(){
        if (pool == null) {
            LOCK.tryLock();
            try {
                pool = new ConnectionPool(new ArrayDeque<>(), new ArrayDeque<>());
                createConnections(pool);
            } catch (SQLException e) {
                e.printStackTrace(); //TODO thr
            } finally {
                LOCK.unlock();
            }
        }
        return pool;
    }

    public void returnConnection(ProxyConnection proxyConnection){
        LOCK.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)){
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
                SEMAPHORE.release();
            }
        } finally {
            LOCK.unlock();
        }
    }

    public ProxyConnection getConnection() throws InterruptedException {
        ProxyConnection connection;
        SEMAPHORE.acquire();
        LOCK.lock();
        try {
            connection = availableConnections.poll();
            connectionsInUse.offer(connection);
        } finally {
            LOCK.unlock();
        }
        return connection;
    }

    private static void createConnections(ConnectionPool pool) throws SQLException {
        LOCK.lock();
        try {
            for (int i = 0; i < 50; i++) {
                ProxyConnection connection = ConnectionFactory.create(pool);
                pool.availableConnections.offer(connection);
            }
        } finally {
            LOCK.unlock();
        }
    }

    public void shutdown() throws SQLException {
        for (ProxyConnection connection: availableConnections) {
            connection.close();
        }
    }
}
