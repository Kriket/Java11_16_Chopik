package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;

/**
 * Created by User on 10.02.2017.
 */
public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return "Bab command. Try again";
    }
}