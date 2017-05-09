package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.RequestParameterName;
import by.epam.webauction.controller.SessionAttributeName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocalization implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        request.getSession(true).setAttribute(SessionAttributeName.LOCAL, request.getParameter(SessionAttributeName.LOCAL));

        return request.getParameter(RequestParameterName.DESTINATION_PAGE);
    }

}
