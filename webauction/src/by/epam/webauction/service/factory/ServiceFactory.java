package by.epam.webauction.service.factory;

import by.epam.webauction.service.DataService;
import by.epam.webauction.service.UserService;
import by.epam.webauction.service.impl.DataServiceImpl;
import by.epam.webauction.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final DataService dataService = new DataServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public DataService getDataService() {
        return dataService;
    }

    public UserService getUserService() {
        return userService;
    }
}
