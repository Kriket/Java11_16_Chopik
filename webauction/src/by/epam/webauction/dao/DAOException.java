package by.epam.webauction.dao;

import by.epam.webauction.exception.ProjectEcxeption;

/**
 * Created by User on 17.02.2017.
 */
public class DAOException extends ProjectEcxeption {
    public static final long serialVersionUID = 1L;

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(String msg, Exception e) {
        super(msg, e);
    }
}
