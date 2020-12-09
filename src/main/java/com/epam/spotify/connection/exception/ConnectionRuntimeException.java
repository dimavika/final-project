package com.epam.spotify.connection.exception;

public class ConnectionRuntimeException extends RuntimeException {

    public ConnectionRuntimeException(String message) {
        super(message);
    }

    public ConnectionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionRuntimeException(Throwable cause) {
        super(cause);
    }

    public ConnectionRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
