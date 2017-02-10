package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

import java.util.Map;

/**
 * Created by User on 10.02.2017.
 */
public class MoreOneBook implements Command {
    public static final String LINE_SEPARATOR = "line.separator";
    public static final String DELIM = "  -  ";

    @Override
    public String execute(String request) {
        Map<String, Integer> map = null;
        StringBuilder stringBuilder = new StringBuilder();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        try {
            map = libraryService.getEmployeesReadedMoreOneBook();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String,Integer> pair : map.entrySet()) {
            stringBuilder.append(pair.getKey()).append(DELIM).append(pair.getValue()).append(System.getProperty(LINE_SEPARATOR));
        }

        return stringBuilder.toString();
    }
}
