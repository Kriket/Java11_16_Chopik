package by.tc.speq.controller;

import by.tc.speq.controller.command.Command;
import by.tc.speq.controller.command.CommandName;
import by.tc.speq.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 17.01.2017.
 */
final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {// friendly- класс и public конструктор - зачем такое?
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Register());
        repository.put(CommandName.RENT, new Rent());
        repository.put(CommandName.RENT_INFO, new GetRentInfo());
        repository.put(CommandName.STOCK_INFO, new GetStockInfo());
        repository.put(CommandName.IS_CONSIST, new IsConsist());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            // Здесь запись в лог
            // ну и где запись в лог?
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
