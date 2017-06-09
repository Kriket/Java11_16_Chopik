package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class UpdateAuc implements ICommand {
    private static final Logger logger = Logger.getLogger(UpdateAuc.class);
    public static final String DELIMITER = ",";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        ArrayList<String> auctionList = new ArrayList<>();
        try {
            auctionList = userService.UpdateAuc();
            logger.info("UpdateAuc was successful");
        } catch (ServiceException e) {
            logger.error("Error while UpdateAuc");
        }

        StringBuilder resultString = new StringBuilder();

        for (String item: auctionList) {
            resultString.append(item).append(DELIMITER);
        }

        return resultString.toString();
    }
}
