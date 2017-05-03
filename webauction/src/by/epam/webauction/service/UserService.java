package by.epam.webauction.service;

import by.epam.webauction.bean.User;

public interface UserService {
    User singUp(String nickname, String password, String email) throws ServiceException;
    User singIn(String email, String password) throws ServiceException;
}
