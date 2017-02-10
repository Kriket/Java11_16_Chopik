package com.epam.library.service.exception;

/**
 * Created by User on 10.02.2017.
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(Exception e) {
        super(e);
    }
}