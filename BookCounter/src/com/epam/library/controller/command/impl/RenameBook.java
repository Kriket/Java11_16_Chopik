package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

/**
 * Created by User on 10.02.2017.
 */
public class RenameBook implements Command {
    public static final String OK_RESPONSE = "Book renamed";
    public static final String ERROR_RESPONSE = "Sorry, but an error occurred";
    private static final Logger loger = Logger.getLogger(RenameBook.class);
    private String response = OK_RESPONSE;

    @Override
    public String execute(String request) {
        loger.info("START renaming book");
        String[] mas = request.split(" ");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        try {
            libraryService.renameBook(mas[1], mas[2]);
            loger.info("END renaming book");
        } catch (ServiceException e) {
            loger.error("Error while renaming book", e);
            response = ERROR_RESPONSE;
        }

        return response;
    }
}
