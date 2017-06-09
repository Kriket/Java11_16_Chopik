package by.epam.webauction.controller.command.impl;

import by.epam.webauction.controller.JspPageName;
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

public class PlaceBet implements ICommand {

    private static final Logger logger = Logger.getLogger(PlaceBet.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String lotId = request.getParameter(RequestParameterName.LOT_ID);
        String currentPrice = request.getParameter(RequestParameterName.CURRENT_PRICE);
        String step = request.getParameter(RequestParameterName.STEP);
        String buyer = request.getParameter(RequestParameterName.BUYER_ID);


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            userService.placeBet(lotId, currentPrice, step, buyer);
            logger.info("Placing bet was successful");
        } catch (ServiceException e) {
            logger.error("Error while placing bet ");
        }

        request.getSession(true).setAttribute(SessionAttributeName.AUCTION, null);

        return JspPageName.SHOW_AUCTION;
    }
}
