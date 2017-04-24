package by.epam.webauction.logic.impl;

import by.epam.webauction.logic.JspPageName;
import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.factory.DAOFactory;
import by.epam.webauction.logic.CommandException;
import by.epam.webauction.logic.ICommand;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 17.02.2017.
 */
public class SignIn implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || login.isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.singIn(login, BCrypt.hashpw(password, BCrypt.gensalt()));
            return JspPageName.USER_PAGE;
        } catch (DAOException e) {
            e.printStackTrace();
            return JspPageName.ERROR_PAGE;
        }
    }
}