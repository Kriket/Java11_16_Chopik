package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.DataService;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitData implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DataService dataService = serviceFactory.getDataService();

        try {
            dataService.init();
        } catch (ServiceException e) {
            // log
        }

        return null;
    }

}
