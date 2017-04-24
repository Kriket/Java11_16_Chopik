package by.epam.webauction.dao;

import by.epam.webauction.bean.User;

public interface UserDAO {
    void singIn(String login, String password) throws DAOException;
    void registration(User user) throws DAOException;
}
