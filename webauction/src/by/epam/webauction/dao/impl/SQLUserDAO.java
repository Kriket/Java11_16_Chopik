package by.epam.webauction.dao.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.util.connection_pool.ConnectionPool;
import by.epam.webauction.dao.util.connection_pool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO {
    public static final String CHECK_USER_STATEMENT = "SELECT u_nickname, u_is_admin FROM users WHERE u_login = ? AND u_password = ?;";

    @Override
    public void singIn(String login, String password) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(CHECK_USER_STATEMENT);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return;
            }

            throw new DAOException("Incorrect login/password");
        } catch (SQLException e) {
            throw new DAOException("Error while executing prepareStatement",e);
        } catch (ConnectionPoolException e) {
            // log.error("ConnectionPools problems", e);
            throw new DAOException("ConnectionPools problems" , e);
        } finally {
            pool.closeConnection(con, preparedStatement, rs);
        }

    }

    @Override
    public void registration(User user) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {

            con = pool.takeConnection();

            //preparedStatement = con.prepareStatement();

            //preparedStatement.setString(1, login);
            //preparedStatement.setString(2, password);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return;
            }

            //throw new DAOException("Incorrect login/password");
        } catch (SQLException e) {
            throw new DAOException("Error while executing prepareStatement",e);
        } catch (ConnectionPoolException e) {
            // log.error("ConnectionPools problems", e);
            throw new DAOException("ConnectionPools problems" , e);
        } finally {
            pool.closeConnection(con, preparedStatement, rs);
        }
    }
}
