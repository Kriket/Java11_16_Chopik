package com.epam.library.service;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.service.exception.ServiceException;

import java.util.Map;

/**
 * Created by User on 10.02.2017.
 */
public interface LibraryService {

    void renameBook(String oldName, String newName) throws ServiceException;
    Map<String, Integer> getEmployeesReadedMoreOneBook() throws ServiceException;
    Map<Employee, Integer> getEmployeesReadedTwoBookOrMore() throws ServiceException;
    void deleteBook(int id) throws ServiceException;
    void addBook(Book book) throws ServiceException;

}
