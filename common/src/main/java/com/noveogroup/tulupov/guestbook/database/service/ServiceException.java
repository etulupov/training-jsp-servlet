package com.noveogroup.tulupov.guestbook.database.service;

/**
 * Service exception.
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
