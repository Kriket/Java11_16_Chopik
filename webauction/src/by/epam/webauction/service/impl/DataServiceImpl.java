package by.epam.webauction.service.impl;

import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.InitDAO;
import by.epam.webauction.dao.factory.DAOFactory;
import by.epam.webauction.service.DataService;
import by.epam.webauction.service.ServiceException;
import org.apache.log4j.Logger;

public class DataServiceImpl implements DataService {

    private static final Logger logger = Logger.getLogger(DataServiceImpl.class);

    @Override
    public void init() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        InitDAO initDAO = daoFactory.getInitDAO();

        try {
            initDAO.init();
            logger.info("Init DAO was successful");
        } catch (DAOException e) {
            logger.error("Error while initialization of DAO", e);
            throw new ServiceException("Error while initialization of DAO", e);
        }
    }

    @Override
    public void destroy() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        InitDAO initDAO = daoFactory.getInitDAO();

        try {
            initDAO.destroy();
            logger.info("Destroy DAO was successful");
        } catch (DAOException e) {
            logger.error("Error while destroying of DAO", e);
            throw new ServiceException("Error while destroying of DAO", e);
        }

    }
}
