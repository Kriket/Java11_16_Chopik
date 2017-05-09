package by.epam.webauction.service.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.factory.DAOFactory;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User singUp(String nickname, String password, String email) throws ServiceException {
        User user = new User();

        if (nickname == null || nickname.isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        if (email == null || email.isEmpty()) {
            return null;
        }

        user.setNickname(nickname);
        user.setPassword(password);
        user.setEmail(email);


        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            return userDAO.registration(user);
        } catch (DAOException e) {
            logger.error("Error while registering the user", e);
            throw new ServiceException("Error while registering the user", e);
        }
    }

    @Override
    public User singIn(String email, String password) throws ServiceException {
        if (email == null || email.isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User user = userDAO.singIn(email);
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        } catch (DAOException e) {
            logger.error("Error while sign in", e);
            throw new ServiceException("Error while sign in", e);
        }

        return null;
    }

}
