package by.tc.speq.service.factory;

import by.tc.speq.service.impl.ClientServiceImpl;
import by.tc.speq.service.impl.ShopServiceImpl;
import by.tc.speq.service.ClientService;
import by.tc.speq.service.ShopService;

/**
 * Created by User on 16.01.2017.
 */
public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final ClientService clientService = new ClientServiceImpl();
    private final ShopService shopService = new ShopServiceImpl();

    private ServiceFactory() {
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ShopService getShopService() {
        return shopService;
    }

}
