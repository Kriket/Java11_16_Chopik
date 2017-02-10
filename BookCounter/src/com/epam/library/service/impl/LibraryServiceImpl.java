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
    @Override
    public void renameBook(String oldName, String newName) throws ServiceException {

        if (oldName == null || oldName.isEmpty()) {
            throw new ServiceException("Incorrect oldName");
        }

        if (newName == null || newName.isEmpty()) {
            throw new ServiceException("Incorrect newName");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.renameBook(oldName, newName);
        } catch (DAOException e) {
            throw new ServiceException("Exception while renaming book", e);
        }

    }

    @Override
    public Map<String, Integer> getEmployeesReadedMoreOneBook() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            return bookDAO.getEmployeesReadedMoreOneBook();
        } catch (DAOException e) {
            throw new ServiceException("Exception while get employees readed more one book", e);
        }
    }

    @Override
    public Map<Employee, Integer> getEmployeesReadedTwoBookOrMore() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            return bookDAO.getEmployeesReadedTwoBookOrMore();
        } catch (DAOException e) {
            throw new ServiceException("Exception while get employees readed two book or more", e);
        }
    }

    @Override
    public void deleteBook(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.deleteBook(id);
        } catch (DAOException e) {
            throw new ServiceException("Exception while deleting book", e);
        }
    }

    @Override
    public void addBook(Book book) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new ServiceException("Exception while add new book", e);
        }
    }
}
