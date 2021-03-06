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

public class DelAuc implements ICommand {
    private static final Logger logger = Logger.getLogger(DelAuc.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String auctionID = request.getParameter(RequestParameterName.AUCTIONS);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            userService.deleteAuction(auctionID);
            logger.info("deleting auction was successful");
        } catch (ServiceException e) {
            logger.error("Error while deleting auction");
        }

        return JspPageName.INDEX_PAGE;
    }
}
