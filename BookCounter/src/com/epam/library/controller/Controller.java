package com.epam.library.controller;

import com.epam.library.controller.command.Command;

/**
 * Created by User on 10.02.2017.
 */
public final class Controller {
    private final char paramDelimeter = ' ';
    private final CommandProvider provider = new CommandProvider();

    public String executeTask(String request){
        String commandName;
        Command command;

        commandName = request.substring(0, request.indexOf(paramDelimeter));
        command = provider.getCommand(commandName);

        String response;
        response = command.execute(request);
        return response;
    }

}