package by.tc.speq.dao.impl;

import by.tc.speq.bean.Category;
import by.tc.speq.bean.SportEquipment;
import by.tc.speq.dao.EquipmentDAO;
import by.tc.speq.dao.exception.DAOException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 14.01.2017.
 */
public class SQLEquipmentDAO implements EquipmentDAO {
    @Override
    public Map<SportEquipment, Integer> getStockInfo() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Map<SportEquipment, Integer> result = new HashMap<>();

        try{
            Class.forName("org.gjt.mm.mysql.Driver");// зачем каждый метод заново грузит драйвер??
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Shop", "root", "root");// и опять, что мы делаем с константными строками в коде?

            st = con.createStatement();
            rs = st.executeQuery("Select * from shop.shop where all_quantity != rent_quantity");
            while (rs.next()) {
                Category temp;

                if (rs.getString(2).toUpperCase().equals("SNOWBOARDING")) {
                    temp = Category.SNOWBOARDING;
                } else if (rs.getString(2).toUpperCase().equals("AIRSOFT")) {
                    temp = Category.AIRSOFT;
                } else {
                    throw new DAOException("Incorrect category");
                }

                result.put(new SportEquipment(rs.getString(1), temp, rs.getInt(3)), rs.getInt(4) - rs.getInt(5));
            }
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
                throw new DAOException(e);// finally не выбрасывает исключений
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

        return result;
    }

    @Override
    public Map<SportEquipment, Integer> getRentInfo() throws DAOException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Map<SportEquipment, Integer> result = new HashMap<>();

        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Shop", "root", "root");

            st = con.createStatement();
            rs = st.executeQuery("Select * from shop.shop");
            while (rs.next()) {
                Category temp;

                if (rs.getString(2).toUpperCase().equals("SNOWBOARDING")) {
                    temp = Category.SNOWBOARDING;
                } else if (rs.getString(2).toUpperCase().equals("AIRSOFT")) {
                    temp = Category.AIRSOFT;
                } else {
                    throw new DAOException("Incorrect category");
                }

                result.put(new SportEquipment(rs.getString(1), temp, rs.getInt(3)), rs.getInt(5));
            }
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

        return result;
    }

    @Override
    public void rentEquipment(SportEquipment sportEquipment) throws DAOException {
        // связываемся с базой данных и арендуем товар

        Connection con = null;
        Statement st = null;
        int i = 0;

        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Shop", "root", "root");

            st = con.createStatement();
            i = st.executeUpdate("update shop.shop set rent_quantity = rent_quantity + 1 where title = '" + sportEquipment.getTitle() + "'and category = " + (sportEquipment.getCategory() == Category.SNOWBOARDING ? 1 : 2) + " and price = " + sportEquipment.getPrice() + ";");

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
