package by.epam.webauction.logic.impl;

import by.epam.webauction.logic.JspPageName;
import by.epam.webauction.logic.CommandException;
import by.epam.webauction.logic.ICommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 17.02.2017.
 */
public class NoSuchCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return JspPageName.ERROR_PAGE;
    }
}
