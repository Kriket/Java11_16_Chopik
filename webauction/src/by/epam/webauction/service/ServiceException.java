package by.epam.webauction.service;

import by.epam.webauction.exception.ProjectException;

public class ServiceException extends ProjectException {
    public static final long serialVersionUID = 1L;

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