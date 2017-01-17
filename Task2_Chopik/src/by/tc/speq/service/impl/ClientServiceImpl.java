package by.tc.speq.service.impl;

import by.tc.speq.bean.User;
import by.tc.speq.dao.UserDAO;
import by.tc.speq.dao.exception.DAOException;
import by.tc.speq.dao.factory.DAOFactory;
import by.tc.speq.service.exception.ServiceException;
import by.tc.speq.service.ClientService;

/**
 * Created by User on 16.01.2017.
 */
public class ClientServiceImpl implements ClientService {
    @Override
    public void singIn(String login, String password) throws ServiceException {

        // проверяем параметры
        if (login == null || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }

        if (password == null || password.isEmpty()) {
            throw new ServiceException("Incorrect password");
        }

        // реализуем функционал логинации пользователя в системе
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.singIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void singOut(String login) throws ServiceException {

        // проверяем параметры
        if (login == null || login.isEmpty()) {
            throw new ServiceException("Incorrect login");
        }

        // stub
    }

    @Override
    public void registration(User user) throws ServiceException {
        // проверяем параметры
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ServiceException("Incorrect login");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ServiceException("Incorrect password");
        }

        // реализуем функционал регистрации пользователя в системе
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            userDAO.registration(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
