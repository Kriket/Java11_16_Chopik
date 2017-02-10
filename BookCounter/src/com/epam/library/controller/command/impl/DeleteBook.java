package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

/**
 * Created by User on 10.02.2017.
 */
public class DeleteBook implements Command {
    public static final String DELETED_RESPONSE = "Deleted";
    public static final String ERROR_RESPONSE = "Sorry, but an error occurred";
    private static final Logger loger = Logger.getLogger(DeleteBook.class);
    private String response = DELETED_RESPONSE;
    @Override
    public String execute(String request) {
        loger.info("START deleting book");
        String[] mas = request.split(" ");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        try {
            libraryService.deleteBook(Integer.parseInt(mas[1]));
            loger.info("END deleting book");
        } catch (ServiceException e) {
            loger.error("Error while deleting book", e);
            response = ERROR_RESPONSE;
        }

        return response;
    }
}
