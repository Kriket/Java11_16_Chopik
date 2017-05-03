package by.epam.webauction.controller.command;

import by.epam.webauction.exception.ProjectException;

public class CommandException extends ProjectException {
    public static final long serialVersionUID = 1L;

    public CommandException(String msg) {
        super(msg);
    }

    public CommandException(String msg, Exception e) {
        super(msg, e);
    }
}
