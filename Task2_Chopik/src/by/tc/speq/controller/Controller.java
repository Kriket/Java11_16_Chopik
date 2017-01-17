package by.tc.speq.controller;

import by.tc.speq.controller.command.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 17.01.2017.
 */
public final class Controller {
    private final CommandProvider provider = new CommandProvider();

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;

        Pattern p = Pattern.compile("(\\S)+");
        Matcher m = p.matcher(request);
        m.find();

        commandName = request.substring(m.start(), m.end());
        executionCommand = provider.getCommand(commandName);

        String response = executionCommand.execute(request.substring(m.end()));

        return response;
    }




}
