package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.JspPageName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOut implements ICommand {

    private static final Logger logger = Logger.getLogger(SignOut.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        request.getSession(false).invalidate();
        logger.info("Sign out was successful");

        return JspPageName.INDEX_PAGE;
    }

}
