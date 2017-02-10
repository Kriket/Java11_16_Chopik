package com.epam.library.service.impl;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.service.LibraryService;
import com.epam.library.service.exception.ServiceException;

import java.util.Map;

/**
 * Created by User on 10.02.2017.
 */
public class LibraryServiceImpl implements LibraryService {
    public static final String INCORRECT_OLDNAME = "Incorrect oldName";
    public static final String INCORRECT_NEWNAME = "Incorrect newName";
    public static final String RENAIMED_ERROR = "Exception while renaming book";
    public static final String GET_MORE_ONE_ERROR = "Exception while get employees who read more one book";
    public static final String GET_TWO_OR_MORE_ERROR = "Exception while get employees who read two book or more";
    public static final String DELETING_ERROR = "Exception while deleting book";
    public static final String ADDING_ERROR = "Exception while add new book";
    @Override
    public void renameBook(String oldName, String newName) throws ServiceException {

        if (oldName == null || oldName.isEmpty()) {
            throw new ServiceException(INCORRECT_OLDNAME);
        }

        if (newName == null || newName.isEmpty()) {
            throw new ServiceException(INCORRECT_NEWNAME);
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.renameBook(oldName, newName);
        } catch (DAOException e) {
            throw new ServiceException(RENAIMED_ERROR, e);
        }

    }

    @Override
    public Map<String, Integer> getEmployeesReadedMoreOneBook() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            return bookDAO.getEmployeesReadedMoreOneBook();
        } catch (DAOException e) {
            throw new ServiceException(GET_MORE_ONE_ERROR, e);
        }
    }

    @Override
    public Map<Employee, Integer> getEmployeesReadedTwoBookOrMore() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            return bookDAO.getEmployeesReadedTwoBookOrMore();
        } catch (DAOException e) {
            throw new ServiceException(GET_TWO_OR_MORE_ERROR, e);
        }
    }

    @Override
    public void deleteBook(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.deleteBook(id);
        } catch (DAOException e) {
            throw new ServiceException(DELETING_ERROR, e);
        }
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new ServiceException(ADDING_ERROR, e);
        }
    }
}
