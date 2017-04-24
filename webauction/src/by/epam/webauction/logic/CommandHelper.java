package by.epam.webauction.logic;

import by.epam.webauction.logic.impl.NoSuchCommand;
import by.epam.webauction.logic.impl.SignIn;
import by.epam.webauction.logic.impl.SignUp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 17.02.2017.
 */
public final class CommandHelper {

    private static final CommandHelper instance = new CommandHelper();

    private Map<CommandName, ICommand> commands = new HashMap<>();

    private CommandHelper() {
        commands.put(CommandName.SIGN_IN, new SignIn());
        commands.put(CommandName.SIGN_UP, new SignUp());
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
