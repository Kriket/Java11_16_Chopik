package by.epam.webauction.controller.command.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.controller.JspPageName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        User user = null;

        try {
            user = userService.singIn(email, password);
        } catch (ServiceException e) {
            return JspPageName.ERROR_PAGE;
        }

        if (user != null) {
            return JspPageName.INDEX_PAGE;
        }

        return null;
    }
}