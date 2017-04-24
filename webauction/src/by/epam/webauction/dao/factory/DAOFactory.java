package by.epam.webauction.dao.factory;

import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.impl.SQLUserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO sqlUser = new SQLUserDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return sqlUser;
    }
}
