package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

/**
 * Created by User on 10.02.2017.
 */
public class DeleteBook implements Command {
    public static final String DELETED_RESPONSE = "DELETED";
    @Override
    public String execute(String request) {
        String[] mas = request.split(" ");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        try {
            libraryService.deleteBook(Integer.parseInt(mas[1]));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return DELETED_RESPONSE;
    }
}
