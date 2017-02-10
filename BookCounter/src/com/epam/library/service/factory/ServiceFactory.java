package com.epam.library.service.factory;

import com.epam.library.service.LibraryService;
import com.epam.library.service.impl.LibraryServiceImpl;

/**
 * Created by User on 10.02.2017.
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory() {
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

}
