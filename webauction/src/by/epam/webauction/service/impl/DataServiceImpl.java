package by.epam.webauction.service.impl;

import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.InitDAO;
import by.epam.webauction.dao.factory.DAOFactory;
import by.epam.webauction.service.DataService;
import by.epam.webauction.service.ServiceException;

public class DataServiceImpl implements DataService {

    @Override
    public void init() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        InitDAO initDAO = daoFactory.getInitDAO();

        try {
            initDAO.init();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void destroy() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        InitDAO initDAO = daoFactory.getInitDAO();

        try {
            initDAO.destroy();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
}
