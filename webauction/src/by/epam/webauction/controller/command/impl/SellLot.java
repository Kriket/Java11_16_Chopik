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

public class SellLot implements ICommand {

    private static final Logger logger = Logger.getLogger(SellLot.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String lotId = request.getParameter(RequestParameterName.LOT_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        try {
            userService.sellLot(lotId);
            logger.info("Selling lot was successful");
        } catch (ServiceException e) {
            logger.error("Error while selling lot");
        }

        request.getSession(true).setAttribute(SessionAttributeName.AUCTION, null);

        return JspPageName.SHOW_AUCTION;
    }
}
