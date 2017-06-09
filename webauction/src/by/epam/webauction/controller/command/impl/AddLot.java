package by.epam.webauction.controller.command.impl;

import by.epam.webauction.bean.Thing;
import by.epam.webauction.bean.User;
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
import java.util.ArrayList;
import java.util.List;

public class AddLot implements ICommand {

    private static final Logger logger = Logger.getLogger(AddLot.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String startPrice = request.getParameter(RequestParameterName.PRICE);
        String[] itemname = request.getParameterValues(RequestParameterName.ITEM_NAME);
        String[] description = request.getParameterValues(RequestParameterName.DESCRIPTION);
        String ownerId = ((User)request.getSession().getAttribute(SessionAttributeName.USER)).getId().toString();
        String auctionID = request.getParameter(RequestParameterName.AUCTIONS);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String idLot = null;
        try {
            idLot = userService.addLot(startPrice, ownerId, auctionID);
            logger.info("adding lot was successful");
        } catch (ServiceException e) {
            logger.error("Error while adding lot");
        }

        List<Thing> thingList = new ArrayList<>();
        Thing thing;
        for (int i = 0; i < itemname.length; i++) {
            thing = new Thing(itemname[i], description[i], ownerId, idLot);
            thingList.add(thing);
        }

        try {
            userService.addThings(thingList);
            logger.info("adding things was successful");
        } catch (ServiceException e) {
            logger.error("Error while adding things");
        }

        return JspPageName.INDEX_PAGE;
    }

}
