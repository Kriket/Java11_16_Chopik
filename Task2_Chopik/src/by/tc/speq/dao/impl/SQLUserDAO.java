package by.tc.speq.dao.impl;

import by.tc.speq.bean.Category;
import by.tc.speq.bean.SportEquipment;
import by.tc.speq.bean.User;
import by.tc.speq.dao.UserDAO;
import by.tc.speq.dao.exception.DAOException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 14.01.2017.
 */
public class SQLUserDAO implements UserDAO {


    @Override
    public void singIn(String login, String password) throws DAOException {
        // связываемся с базой данных и проверяем корректность логина и пароля
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Shop", "root", "root");

            st = con.createStatement();
            rs = st.executeQuery("select * from shop.users;");
            while (rs.next()) {

                if ((login.equals(rs.getString(1))) && (password.equals(rs.getString(2)))) {
                    return;
                }
            }

            throw new DAOException("Incorrect login/password");

        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void registration(User user) throws DAOException {
        // связываемся с базой данных и добавляем пользователя
        Connection con = null;
        Statement st = null;
        int i = 0;

        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Shop", "root", "root");

            st = con.createStatement();
            i = st.executeUpdate("insert into shop.users(login, password) values ('" + user.getLogin() +"', '" + user.getPassword() + "'); ");
            // я так понимаю, что PreparedStatement тобой явно не изучался
            
        } catch (ClassNotFoundException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

}
