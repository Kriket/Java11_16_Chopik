package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.DataService;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitData implements ICommand {

    private static final Logger logger = Logger.getLogger(InitData.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DataService dataService = serviceFactory.getDataService();

        try {
            dataService.init();
            logger.info("Init data was successful");
        } catch (ServiceException e) {
            logger.error("Error of data initialization", e);
        }

        return null;
    }

}
