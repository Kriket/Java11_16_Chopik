package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.domain.Book;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

/**
 * Created by User on 10.02.2017.
 */
public class AddBook implements Command {
    public static final String ADDED_RESPONSE = "ADDED";
    @Override
    public String execute(String request) {
        String[] mas = request.split(" ");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        try {
            libraryService.addBook(new Book(Integer.parseInt(mas[1]), mas[2], Integer.parseInt(mas[3]),mas[4]));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return ADDED_RESPONSE;
    }
}
