package com.epam.library.controller;

import com.epam.library.controller.command.Command;
import com.epam.library.controller.command.CommandName;
import com.epam.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 10.02.2017.
 */
final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.RENAME_BOOK, new RenameBook());
        repository.put(CommandName.READED_MORE_THEN_ONE_BOOK, new MoreOneBook());
        repository.put(CommandName.READED_TWO_OR_MORE_BOOK, new TwoBookOrMore());
        repository.put(CommandName.DELETE_BOOK, new DeleteBook());
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            // запись в лог
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
