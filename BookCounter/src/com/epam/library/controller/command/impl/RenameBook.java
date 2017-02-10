package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

/**
 * Created by User on 10.02.2017.
 */
public class RenameBook implements Command {
    public static final String OK_RESPONSE = "OK";
    @Override
    public String execute(String request) {
        String[] mas = request.split(" ");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        try {
            libraryService.renameBook(mas[1], mas[2]);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return OK_RESPONSE;
    }
}
