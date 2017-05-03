package by.epam.webauction.controller.command.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.controller.JspPageName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.factory.ServiceFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String nickname = request.getParameter("nickname");
        String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
        String email = request.getParameter("email");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        User user = null;

        try {
            user = userService.singUp(nickname, password, email);
        } catch (ServiceException e) {
            return JspPageName.ERROR_PAGE;
        }

        if (user != null) {
            return JspPageName.CONGRATULATION_WITH_REGISTRATION;
        }

        return null;
    }
}
