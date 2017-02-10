package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;

/**
 * Created by User on 10.02.2017.
 */
public class WrongRequest implements Command {
    public static final String BAD_COMMAND = "Bab command. Try again";

    @Override
    public String execute(String request) {
        return BAD_COMMAND;
    }
}