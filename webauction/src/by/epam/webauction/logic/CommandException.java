package by.epam.webauction.logic;

import by.epam.webauction.exception.ProjectEcxeption;

/**
 * Created by User on 17.02.2017.
 */
public class CommandException extends ProjectEcxeption {
    public static final long serialVersionUID = 1L;

    public CommandException(String msg) {
        super(msg);
    }

    public CommandException(String msg, Exception e) {
        super(msg, e);
    }
}
