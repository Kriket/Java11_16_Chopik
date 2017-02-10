package com.epam.library.dao.factory;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.impl.SQLBookDAO;
import org.apache.log4j.Logger;

/**
 * Created by User on 10.02.2017.
 */
public class DAOFactory {
    private static final String DRIVER = "org.gjt.mm.mysql.Driver";
    private static final Logger loger = Logger.getLogger(DAOFactory.class);
    private static final DAOFactory instance = new DAOFactory();

    private final BookDAO bookDAO = new SQLBookDAO();

    private DAOFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            loger.error("Error while loading driver", e);
        }
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public BookDAO getBookDAO() {
    return bookDAO;
}
}
