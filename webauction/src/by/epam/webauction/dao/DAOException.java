package by.epam.webauction.dao;

import by.epam.webauction.exception.ProjectException;

public class DAOException extends ProjectException {
    public static final long serialVersionUID = 1L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Exception e) {
        super(message, e);
    }

    public DAOException(Exception e) {
        super(e);
    }
}
