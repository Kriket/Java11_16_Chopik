package by.epam.webauction.controller.command.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.controller.JspPageName;
import by.epam.webauction.controller.RequestParameterName;
import by.epam.webauction.controller.SessionAttributeName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements ICommand {

    private static final Logger logger = Logger.getLogger(SignIn.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String email = request.getParameter(RequestParameterName.EMAIL);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        User user = null;

        try {
            user = userService.singIn(email, password);
        } catch (ServiceException e) {
            logger.warn("Sign in was unsuccessful");
            return JspPageName.ERROR_PAGE;
        }

        if (user != null) {
            request.getSession(true).setAttribute(SessionAttributeName.USER, user);
            logger.info("Sign in was successful");
            return JspPageName.INDEX_PAGE;
        }

        return null;
    }
}