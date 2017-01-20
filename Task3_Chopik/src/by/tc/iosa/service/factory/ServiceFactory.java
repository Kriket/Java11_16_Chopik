package by.tc.iosa.service.factory;

import by.tc.iosa.service.impl.AllFileService;

/**
 * Created by User on 20.01.2017.
 */
public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final AllFileService allFileService = new AllFileService();

    private ServiceFactory() {
    }

    public AllFileService getAllFileService() {
        return allFileService;
    }

}