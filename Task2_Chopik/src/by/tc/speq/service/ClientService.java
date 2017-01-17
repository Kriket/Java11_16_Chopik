package by.tc.speq.service;

import by.tc.speq.bean.User;
import by.tc.speq.service.exception.ServiceException;

/**
 * Created by User on 16.01.2017.
 */
public interface ClientService {

    void singIn(String login, String password) throws ServiceException;
    void singOut(String login) throws ServiceException;
    void registration(User user) throws ServiceException;

}
