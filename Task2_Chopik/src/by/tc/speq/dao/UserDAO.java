package by.tc.speq.dao;

import by.tc.speq.bean.User;
import by.tc.speq.dao.exception.DAOException;

/**
 * Created by User on 14.01.2017.
 */


public interface UserDAO {

    void singIn(String login, String password) throws DAOException;
    void registration(User user) throws DAOException;

}
