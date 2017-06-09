package by.epam.webauction.controller.command.impl;

import by.epam.webauction.bean.Auction;
import by.epam.webauction.controller.RequestParameterName;
import by.epam.webauction.controller.SessionAttributeName;
import by.epam.webauction.controller.command.CommandException;
import by.epam.webauction.controller.command.ICommand;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshAuction implements ICommand {

    private static final Logger logger = Logger.getLogger(RefreshAuction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String auctionID = request.getParameter(RequestParameterName.AUCTIONS);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        Auction auction = null;

        try {
            auction = userService.refreshAuction(auctionID);
            logger.info("Refresh auction was successful");
        } catch (ServiceException e) {
            logger.error("Error while refreshing auction");
        }

        request.getSession(true).setAttribute(SessionAttributeName.AUCTION, auction);

        return request.getParameter(RequestParameterName.DESTINATION_PAGE);

    }
}
