package com.epam.library.dao;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;

import java.util.Map;

/**
 * Created by User on 10.02.2017.
 */
public interface BookDAO {

    void addBook(Book book) throws DAOException;
    void renameBook(String oldName, String newName) throws DAOException;
    void deleteBook(int id) throws DAOException;
    Map<String, Integer> getEmployeesReadedMoreOneBook() throws DAOException;
    Map<Employee, Integer> getEmployeesReadedTwoBookOrMore() throws DAOException;


}
