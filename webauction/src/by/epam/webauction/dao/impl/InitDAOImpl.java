package by.epam.webauction.dao.impl;

import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.InitDAO;
import by.epam.webauction.dao.util.connection_pool.ConnectionPool;
import by.epam.webauction.dao.util.connection_pool.ConnectionPoolException;

public class InitDAOImpl implements InitDAO {

    @Override
    public void init() throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void destroy() throws DAOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.dispose();

    }
}
