package by.tc.speq.controller.command.impl;

import by.tc.speq.controller.command.Command;

/**
 * Created by User on 17.01.2017.
 */
public class WrongRequest implements Command {

    @Override
    public String execute(String request) {
        return "Bab command. Try again";
    }
}
