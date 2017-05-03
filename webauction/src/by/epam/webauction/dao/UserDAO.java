package by.epam.webauction.dao;

import by.epam.webauction.bean.User;

public interface UserDAO {
    User singIn(String login) throws DAOException;
    User registration(User user) throws DAOException;
}
