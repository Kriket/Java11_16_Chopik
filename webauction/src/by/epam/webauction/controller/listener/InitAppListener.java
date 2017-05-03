package by.epam.webauction.controller.listener;

import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.CommandHelper;
import by.epam.webauction.controller.command.CommandName;
import by.epam.webauction.controller.command.ICommand;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitAppListener implements ServletContextListener{

    // private static final Loger

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        CommandHelper commandHelper = CommandHelper.getInstance();
        ICommand command = commandHelper.getCommand(CommandName.INIT_DATA.toString());

        try {
            command.execute(null, null);
        } catch (CommandException e) {
            // log
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        CommandHelper commandHelper = CommandHelper.getInstance();
        ICommand command = commandHelper.getCommand(CommandName.DESTROY_DATA.toString());

        try {
            command.execute(null, null);
        } catch (CommandException e) {
            // log
        }

    }
}
