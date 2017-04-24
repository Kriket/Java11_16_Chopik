package by.epam.webauction.logic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 17.02.2017.
 */
public interface ICommand {
    public String execute(HttpServletRequest request) throws CommandException;
}
