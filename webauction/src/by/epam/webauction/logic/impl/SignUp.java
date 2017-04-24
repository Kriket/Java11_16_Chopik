package by.epam.webauction.logic.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.logic.JspPageName;
import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.factory.DAOFactory;
import by.epam.webauction.logic.CommandException;
import by.epam.webauction.logic.ICommand;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;

public class SignUp implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");

        User user = new User();

        if (login == null || login.isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        /*if (nickname == null || nickname.isEmpty()) {
            return null;
        }

        if (name == null || name.isEmpty()) {
            return null;
        }

        if (surname == null || surname.isEmpty()) {
            return null;
        }

        if (email == null || email.isEmpty()) {
            return null;
        }*/

        user.setLogin(login);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        user.setNickname(nickname);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);


        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.registration(user);

            // Поздравляем вы зарегестрировались
            return JspPageName.UFTER_SIGN_UP_PAGE;
        } catch (DAOException e) {
            e.printStackTrace();
            return JspPageName.ERROR_PAGE;
        }
    }
}
