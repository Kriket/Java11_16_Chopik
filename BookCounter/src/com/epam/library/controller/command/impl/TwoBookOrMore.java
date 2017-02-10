package com.epam.library.controller.command.impl;

import com.epam.library.controller.command.Command;
import com.epam.library.domain.Employee;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by User on 10.02.2017.
 */
public class TwoBookOrMore implements Command {
    public static final String LINE_SEPARATOR = "line.separator";
    public static final String DELIM = "  -  ";
    public static final String ERROR_RESPONSE = "Sorry, but an error occurred";
    private static final Logger loger = Logger.getLogger(TwoBookOrMore.class);

    @Override
    public String execute(String request) {
        loger.info("START getting employees who read less than two book");
        Map<Employee, Integer> map = null;
        StringBuilder stringBuilder = new StringBuilder();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();
        try {
            map = libraryService.getEmployeesReadedTwoBookOrMore();
            loger.info("END getting employees who read less than two book");
        } catch (ServiceException e) {
            loger.error("Error while getting employees who read less than two book", e);
            return ERROR_RESPONSE;
        }

        for (Map.Entry<Employee,Integer> pair : map.entrySet()) {
            stringBuilder.append(pair.getKey().getName());
            stringBuilder.append(DELIM);
            stringBuilder.append(pair.getKey().getDateOfBirth());
            stringBuilder.append(DELIM);
            stringBuilder.append(pair.getValue());
            stringBuilder.append(System.getProperty(LINE_SEPARATOR));
        }

        return stringBuilder.toString();
    }
}
