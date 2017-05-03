package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.JspPageName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoSuchCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return JspPageName.ERROR_PAGE;
    }
}
