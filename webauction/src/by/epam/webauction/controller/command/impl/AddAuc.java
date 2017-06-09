package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.JspPageName;
import by.epam.webauction.controller.RequestParameterName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAuc implements ICommand {

    private static final Logger logger = Logger.getLogger(AddAuc.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String aucName = request.getParameter(RequestParameterName.AUCTION_NAME);
        String step = request.getParameter(RequestParameterName.STEP);
        String currency = request.getParameter(RequestParameterName.CURRENCY);
        String startdate = request.getParameter(RequestParameterName.AUCTION_START_DAY);


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            userService.addAuction(aucName, step, currency, startdate);
            logger.info("adding lot was successful");
        } catch (ServiceException e) {
            logger.error("Error while adding lot");
        }

        return JspPageName.INDEX_PAGE;
    }
}
