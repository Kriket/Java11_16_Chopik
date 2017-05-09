package by.epam.webauction.controller.command;

import by.epam.webauction.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandHelper {

    private static final CommandHelper instance = new CommandHelper();

    private Map<CommandName, ICommand> commands = new HashMap<>();

    private CommandHelper() {
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
        commands.put(CommandName.SIGN_OUT, new SignOut());
        commands.put(CommandName.INIT_DATA, new InitData());
        commands.put(CommandName.DESTROY_DATA, new DestroyData());
        commands.put(CommandName.CHANGE_LOCALIZATION, new ChangeLocalization());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }

    public static CommandHelper getInstance() {
        return instance;
    }

    public ICommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        ICommand command;
        if (null != name) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }
}
