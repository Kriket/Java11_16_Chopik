package by.epam.webauction.controller.command.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.controller.JspPageName;
import by.epam.webauction.controller.RequestParameterName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.factory.ServiceFactory;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp implements ICommand {

    private static final Logger logger = Logger.getLogger(SignUp.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String nickname = request.getParameter(RequestParameterName.NICKNAME);
        String password = BCrypt.hashpw(request.getParameter(RequestParameterName.PASSWORD), BCrypt.gensalt());
        String email = request.getParameter(RequestParameterName.EMAIL);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        User user = null;

        try {
            user = userService.singUp(nickname, password, email);
        } catch (ServiceException e) {
            logger.warn("Sign up was unsuccessful");
            return JspPageName.ERROR_PAGE;
        }

        if (user != null) {
            logger.info("Sign up was successful");
            return JspPageName.CONGRATULATION_WITH_REGISTRATION;
        }

        return null;
    }
}
