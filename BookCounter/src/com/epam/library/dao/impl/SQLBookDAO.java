package com.epam.library.dao.impl;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 10.02.2017.
 */
public class SQLBookDAO implements BookDAO {
    private static final Logger loger = Logger.getLogger(SQLBookDAO.class);
    public static final String URL = "jdbc:mysql://127.0.0.1/cbrc_db";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";

    @Override
    public void addBook(Book book) throws DAOException {

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            preparedStatement = con.prepareStatement("Insert into book(ID,BRIEF, PUBLISH_YEAR, AUTHOR)\n" +
                    "VALUES (?,?,?,?);");
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getBrief());
            preparedStatement.setInt(3, book.getDateOfPublishing());
            preparedStatement.setString(4, book.getAuthor());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing PreparedStatement", e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Connection", e);
            }

        }

    }

    @Override
    public void renameBook(String oldName, String newName)  throws DAOException {

        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            st = con.createStatement();
            st.executeUpdate("update book set brief = '" + newName + "' where brief = '" + oldName +"';");
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Statement", e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Connection", e);
            }

        }

    }

    @Override
    public void deleteBook(int id) throws DAOException {

        Connection con = null;
        Statement st = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            st = con.createStatement();
            st.executeUpdate("delete from book where id = '" + id + "';");
        } catch (SQLException e) {
           throw new DAOException(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Statement", e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Connection", e);
            }

        }

    }

    @Override
    public Map<String, Integer> getEmployeesReadedMoreOneBook() throws DAOException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Map<String, Integer> result = new HashMap<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            st = con.createStatement();
            rs = st.executeQuery("select employee.name,\n" +
                                "\t\tcount(book_id)\n" +
                                " from employee inner join employee_book\n" +
                                "\ton employee.ID = employee_book.EMPLOYEE_ID  \n" +
                                " group by employee.NAME\n" +
                                " having count(book_id) > 1\n" +
                                " order by BOOK_ID asc;");

            while (rs.next()) {
                result.put(rs.getString(1), rs.getInt(2));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Statement", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing ResultSet", e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Connection", e);
            }

        }

        return result;
    }

    @Override
    public Map<Employee, Integer> getEmployeesReadedTwoBookOrMore() throws DAOException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Map<Employee, Integer> result = new HashMap<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            st = con.createStatement();
            rs = st.executeQuery("select employee.name, \n" +
                    "\temployee.DATE_OF_BIRTH,\n" +
                    "\t\tcount(book_id)\n" +
                    " from employee inner join employee_book\n" +
                    "\ton employee.ID = employee_book.EMPLOYEE_ID  \n" +
                    " group by employee.NAME\n" +
                    " having count(book_id)  < 2\n" +
                    " order by BOOK_ID asc;");

            while (rs.next()) {
               result.put(new Employee(rs.getString(1), rs.getDate(2)), rs.getInt(3));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Statement", e);
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing ResultSet", e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                loger.error("Error while closing Connection", e);
            }

        }

        return result;
    }

}
