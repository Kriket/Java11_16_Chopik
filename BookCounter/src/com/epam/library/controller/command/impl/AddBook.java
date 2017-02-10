package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.domain.Book;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

/**
 * Created by User on 10.02.2017.
 */
public class AddBook implements Command {
    private static final Logger loger = Logger.getLogger(AddBook.class);
    public static final String ADDED_RESPONSE = "Added";
    public static final String ERROR_RESPONSE = "Sorry, but an error occurred";
    private String response = ADDED_RESPONSE;
    @Override
    public String execute(String request) {
        loger.info("START adding book");
        String[] mas = request.split(" ");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        try {
            libraryService.addBook(new Book(Integer.parseInt(mas[1]), mas[2], Integer.parseInt(mas[3]),mas[4]));
            loger.info("END adding book");
        } catch (ServiceException e) {
            loger.error("Error while adding book", e);
            response = ERROR_RESPONSE;
        }

        return response;
    }
}
