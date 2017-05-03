package by.epam.webauction.dao.impl;

import by.epam.webauction.bean.User;
import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.util.connection_pool.ConnectionPool;
import by.epam.webauction.dao.util.connection_pool.ConnectionPoolException;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    public static final String CHECK_USER_STATEMENT = "SELECT u_id, u_nickname, u_is_admin, u_password FROM users WHERE u_email = ?;";
    public static final String ADD_USER = "INSERT INTO users (u_is_admin, u_nickname, u_password, u_email) VALUES (FALSE, ?, ?, ?);";
    public static final String LAST_ID = "SELECT last_insert_id();";
    @Override
    public User singIn(String email) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(CHECK_USER_STATEMENT);

            preparedStatement.setString(1, email);

            rs = preparedStatement.executeQuery();

            if (rs != null) {
                User user = new User();
                rs.next();
                user.setId(rs.getInt(1));
                user.setNickname(rs.getString(2));
                user.setEmail(email);
                user.setPassword(rs.getString(4));

                return user;
            }

            throw new DAOException("Incorrect email/password");
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
    public User registration(User user) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(ADD_USER);

            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.execute();
            if (preparedStatement.getUpdateCount() == 1) {
                statement = con.createStatement();
                resultSet = statement.executeQuery(LAST_ID);

                resultSet.next();
                int userID = resultSet.getInt(1);
                user.setId(userID);

                return user;
            } else {
                return null;
            }


        } catch (SQLException e) {
            throw new DAOException("Error while executing statement",e);
        } catch (ConnectionPoolException e) {
            // log.error("ConnectionPools problems", e);
            throw new DAOException("ConnectionPools problems" , e);
        } finally {
            pool.closeConnection(con, preparedStatement);
            pool.closeConnection(con, statement, resultSet);
        }
    }
}
