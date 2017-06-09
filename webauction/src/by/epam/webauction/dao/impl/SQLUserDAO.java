package by.epam.webauction.dao.impl;

import by.epam.webauction.bean.Auction;
import by.epam.webauction.bean.Lot;
import by.epam.webauction.bean.Thing;
import by.epam.webauction.bean.User;
import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.util.connection_pool.ConnectionPool;
import by.epam.webauction.dao.util.connection_pool.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDAO implements UserDAO {
    public static final String CHECK_USER_STATEMENT = "SELECT u_id, u_nickname, u_is_admin, u_password FROM users WHERE u_email = ?";
    public static final String ADD_USER = "INSERT INTO users (u_is_admin, u_nickname, u_password, u_email) VALUES (0, ?, ?, ?)";
    public static final String ADD_LOT = "INSERT INTO lots (l_start_price, l_current_price, l_id_seller, l_id_auction) VALUES (?, ?, ?, ?)";
    public static final String ADD_THING = "INSERT INTO THINGS (T_NAME, T_DESCRIPTION, T_ID_OWNER) VALUES (?, ?, ?)";
    public static final String ADD_AUCTION = "INSERT INTO AUCTIONS (A_STEP, A_CURRENCY, A_IS_ACTIVE, A_START, A_NAME) VALUES (?, ?, 0, ?, ?)";
    public static final String DELETE_AUCTION = "DELETE FROM AUCTIONS WHERE A_ID = ?";
    public static final String LAST_ID = "SELECT last_insert_id();";
    public static final String GET_AUC_ID_AND_NAMES = "SELECT A_ID, A_NAME FROM AUCTIONS";
    public static final String GET_AUC_ID_AND_NAMES_FOR_DELETE_LOTS = "SELECT A_ID, A_NAME FROM AUCTIONS WHERE A_ID IN (SELECT L_ID_AUCTION FROM LOTS WHERE L_ID_SELLER = ?)";
    public static final String GET_AUC_INFO = "SELECT A_STEP, A_CURRENCY, A_IS_ACTIVE, A_START, A_NAME FROM AUCTIONS WHERE A_ID = ?";
    public static final String GET_LOTS_INFO = "SELECT l_id, l_start_price, l_current_price, l_finish_price, l_is_sell, l_id_seller, l_id_buyer FROM LOTS WHERE l_id_auction = ? and l_is_sell = 0";
    public static final String GET_THINGS_INFO = "SELECT t_name, t_description, t_id_owner FROM THINGS t JOIN th_m2m_lt tl ON t.t_id = tl.t_id WHERE tl.l_id = ?";
    public static final String ADD_TH_IN_LOT = "INSERT INTO TH_M2M_LT values(?,?)";
    public static final String DEL_TH_IN_LOT = "DELETE FROM TH_M2M_LT WHERE L_ID IN (SELECT L_ID FROM LOTS WHERE L_ID_AUCTION = ?)";
    public static final String DEL_LOTS = "DELETE FROM LOTS WHERE L_ID_AUCTION = ?";
    public static final String PLACE_BET = "update lots set l_current_price = ?, l_last_bettor = ? where l_id = ?";
    public static final String SELL_LOT = "update lots set l_finish_price = lots.l_current_price, l_is_sell = 1, l_id_buyer = lots.l_last_bettor where l_id = ?";
    public static final String GAP = " ";

    private static final Logger logger = Logger.getLogger(SQLUserDAO.class);

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
                user.setIsAdmin(rs.getBoolean(3));
                user.setPassword(rs.getString(4));

                return user;
            }



            logger.info("Incorrect email/password");
            throw new DAOException("Incorrect email/password");
        } catch (SQLException e) {
            logger.error("Error while executing prepareStatement", e);
            throw new DAOException("Error while executing prepareStatement", e);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPools problems", e);
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
                logger.info("Registration was successful");

                try {
                    statement = con.createStatement();
                    resultSet = statement.executeQuery(LAST_ID);
                    resultSet.next();
                    int userID = resultSet.getInt(1);
                    user.setId(userID);
                } catch (SQLException e) {
                    logger.error("Error while getting LAST_ID", e);
                }

                return user;
            } else {
                return null;
            }


        } catch (SQLException e) {
            logger.error("Error while executing registration statement",e);
            throw new DAOException("Error while executing registration statement",e);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool problems", e);
            throw new DAOException("ConnectionPool problems", e);
        } finally {
            pool.closeConnection(con, preparedStatement);
            pool.closeConnection(con, statement, resultSet);
        }
    }

    @Override
    public String addLot(String startPrice, String sellerId, String auctionId) throws DAOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String lotId = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(ADD_LOT);

            preparedStatement.setString(1, startPrice);
            preparedStatement.setString(2, startPrice);
            preparedStatement.setString(3, sellerId);
            preparedStatement.setString(4, auctionId);

            preparedStatement.executeUpdate();


            if (preparedStatement.getUpdateCount() != 0) {
                logger.info("Adding lots to database was successful");

                /*resultSet = preparedStatement.getGeneratedKeys();
                resultSet.last();
                lotId = resultSet.getString("l_id");*/
                try {
                    statement = con.createStatement();
                    resultSet = statement.executeQuery(LAST_ID);
                    resultSet.next();
                    lotId = String.valueOf(resultSet.getInt(1));
                } catch (SQLException e) {
                    logger.error("Error while getting LAST_ID", e);
                }


            }

        } catch (SQLException e) {
            logger.error("Error while executing adding lot statement",e);
            throw new DAOException("Error while executing adding lot statement",e);
        } catch (ConnectionPoolException e) {
            logger.error("ConnectionPool problems", e);
            throw new DAOException("ConnectionPool problems", e);
        } finally {
            pool.closeConnection(con, preparedStatement);
            pool.closeConnection(con, statement, resultSet);
        }

        return lotId;
    }

    @Override
    public Boolean addAuction(String aucName, String step, String currency, String startdate) throws DAOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(ADD_AUCTION);
            preparedStatement.setString(1, step);
            preparedStatement.setString(2, currency);
            preparedStatement.setString(3, startdate);
            preparedStatement.setString(4, aucName);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing addAuction statement",e);
            throw new DAOException("Error while executing addAuction statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing addAuction statement",e);
            throw new DAOException("Error while executing addAuction statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement);
        }

        return true;
    }

    @Override
    public Boolean deleteAuction(String auctionId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(DEL_TH_IN_LOT);
            preparedStatement.setString(1, auctionId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(DELETE_AUCTION);
            preparedStatement.setString(1, auctionId);

            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing deleteAuction statement",e);
            throw new DAOException("Error while executing deleteAuction statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing deleteAuction statement",e);
            throw new DAOException("Error while executing deleteAuction statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement);
        }

        return true;
    }

    @Override
    public Boolean deleteLots(String auctionId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(DEL_TH_IN_LOT);
            preparedStatement.setString(1, auctionId);
            preparedStatement.executeUpdate();

            preparedStatement = con.prepareStatement(DEL_LOTS);
            preparedStatement.setString(1, auctionId);
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing deleteLots statement",e);
            throw new DAOException("Error while executing deleteLots statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing deleteLots statement",e);
            throw new DAOException("Error while executing deleteLots statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement);
        }

        return true;
    }

    @Override
    public Boolean addThings(List<Thing> thingList) throws DAOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(ADD_THING);
            preparedStatement1 = con.prepareStatement(ADD_TH_IN_LOT);
            for (Thing item : thingList) {

                preparedStatement.setString(1, item.getName());
                preparedStatement.setString(2, item.getDescription());
                preparedStatement.setString(3, item.getOwnerId());

                preparedStatement.executeUpdate();

                try {
                    statement = con.createStatement();
                    resultSet = statement.executeQuery(LAST_ID);
                    resultSet.next();
                    preparedStatement1.setString(1, resultSet.getString(1));
                } catch (SQLException e) {
                    logger.error("Error while getting LAST_THING_ID", e);
                }

                preparedStatement1.setString(2, item.getLotId());
                preparedStatement1.executeUpdate();
            }

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing addThings statement",e);
            throw new DAOException("Error while executing addThings statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing addThings statement",e);
            throw new DAOException("Error while executing addThings statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement);
            pool.closeConnection(con, statement);
        }

        return null;
    }

    @Override
    public ArrayList<String> UpdateAuc() throws DAOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            con = pool.takeConnection();

            statement = con.createStatement();
            resultSet = statement.executeQuery(GET_AUC_ID_AND_NAMES);

            while (resultSet.next()) {
                String temp = resultSet.getString(1) + GAP + resultSet.getString(2);
                result.add(temp);
            }

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } finally {
            pool.closeConnection(con, statement, resultSet);
        }

        return result;
    }

    @Override
    public ArrayList<String> UpdateAuc(String sellerId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(GET_AUC_ID_AND_NAMES_FOR_DELETE_LOTS);
            preparedStatement.setString(1, sellerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String temp = resultSet.getString(1) + GAP + resultSet.getString(2);
                result.add(temp);
            }

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement, resultSet);
        }

        return result;
    }

    @Override
    public Auction getAuctionInfo(String auctionId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Auction auction = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(GET_AUC_INFO);
            preparedStatement.setString(1, auctionId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                auction = new Auction(auctionId, resultSet.getString(1), by.epam.webauction.bean.Currency.valueOf(resultSet.getString(2)), resultSet.getBoolean(3), resultSet.getDate(4), resultSet.getString(5));
                return auction;
            }

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement, resultSet);
        }

        return null;
    }

    @Override
    public List<Lot> getLotsInfo(String auctionId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Lot> lots = new ArrayList<>();

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(GET_LOTS_INFO);
            preparedStatement.setString(1, auctionId);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Lot lot = new Lot(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getBoolean(5), resultSet.getString(6), resultSet.getString(7));
                lots.add(lot);
            }

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement, resultSet);
        }

        return lots;
    }

    @Override
    public List<Thing> getThingsInfo(String lotId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Thing> things = new ArrayList<>();

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(GET_THINGS_INFO);
            preparedStatement.setString(1, lotId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Thing thing = new Thing(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                things.add(thing);
            }

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing UpdateAuc statement",e);
            throw new DAOException("Error while executing UpdateAuc statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement, resultSet);
        }

        return things;
    }

    @Override
    public void placeBet(String lotId, String currentPrice, String buyer) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(PLACE_BET);
            preparedStatement.setString(1, currentPrice);
            preparedStatement.setString(2, buyer);
            preparedStatement.setString(3, lotId);
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing placing bet statement",e);
            throw new DAOException("Error while executing placing bet statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing placing bet statement",e);
            throw new DAOException("Error while executing placing bet statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement);
        }
    }

    @Override
    public void sellLot(String lotId) throws DAOException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = pool.takeConnection();

            preparedStatement = con.prepareStatement(SELL_LOT);
            preparedStatement.setString(1, lotId);
            preparedStatement.executeUpdate();

        } catch (ConnectionPoolException e) {
            logger.error("Error while executing selling lot statement",e);
            throw new DAOException("Error while executing selling lot statement",e);
        } catch (SQLException e) {
            logger.error("Error while executing selling lot statement",e);
            throw new DAOException("Error while executing selling lot statement",e);
        } finally {
            pool.closeConnection(con, preparedStatement);
        }
    }
}
