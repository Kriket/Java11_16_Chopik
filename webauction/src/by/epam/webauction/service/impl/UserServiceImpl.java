package by.epam.webauction.service.impl;

import by.epam.webauction.bean.Auction;
import by.epam.webauction.bean.Lot;
import by.epam.webauction.bean.Thing;
import by.epam.webauction.bean.User;
import by.epam.webauction.dao.DAOException;
import by.epam.webauction.dao.UserDAO;
import by.epam.webauction.dao.factory.DAOFactory;
import by.epam.webauction.service.ServiceException;
import by.epam.webauction.service.UserService;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User singUp(String nickname, String password, String email) throws ServiceException {
        User user = new User();

        if (nickname == null || nickname.isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        if (email == null || email.isEmpty()) {
            return null;
        }

        user.setNickname(nickname);
        user.setPassword(password);
        user.setEmail(email);


        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            return userDAO.registration(user);
        } catch (DAOException e) {
            logger.error("Error while registering the user", e);
            throw new ServiceException("Error while registering the user", e);
        }
    }

    @Override
    public User singIn(String email, String password) throws ServiceException {
        if (email == null || email.isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User user = userDAO.singIn(email);
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        } catch (DAOException e) {
            logger.error("Error while sign in", e);
            throw new ServiceException("Error while sign in", e);
        }

        return null;
    }

    @Override
    public String addLot(String startPrice, String sellerId, String auctionId) throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        String idLot = null;

        try {
            idLot = userDAO.addLot(startPrice, sellerId, auctionId);
        } catch (DAOException e) {
            logger.error("Error while adding lot to DAO");
            throw new ServiceException("Error while adding lot to DAO", e);
        }

        return idLot;
    }

    @Override
    public Boolean addAuction(String aucName, String step, String currency, String startdate) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.addAuction(aucName, step, currency, startdate);
            return true;
        } catch (DAOException e) {
            logger.error("Error while adding things to DAO");
            throw new ServiceException("Error while adding things to DAO", e);
        }
    }

    @Override
    public Boolean deleteAuction(String auctionId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.deleteAuction(auctionId);
            return true;
        } catch (DAOException e) {
            logger.error("Error while deleting auction from DAO");
            throw new ServiceException("Error while deleting auction from DAO", e);
        }
    }

    @Override
    public Boolean deleteLots(String auctionId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.deleteLots(auctionId);
            return true;
        } catch (DAOException e) {
            logger.error("Error while deleting lot from DAO");
            throw new ServiceException("Error while deleting lot from DAO", e);
        }
    }

    @Override
    public Boolean addThings(List<Thing> thingList) throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.addThings(thingList);
            return true;
        } catch (DAOException e) {
            logger.error("Error while adding things to DAO");
            throw new ServiceException("Error while adding things to DAO", e);
        }
    }

    @Override
    public ArrayList<String> UpdateAuc() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            return userDAO.UpdateAuc();
        } catch (DAOException e) {
            logger.error("Error while UpdateAuc to DAO");
            throw new ServiceException("Error while UpdateAuc to DAO", e);
        }
    }

    @Override
    public ArrayList<String> UpdateAuc(String sellerId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            return userDAO.UpdateAuc(sellerId);
        } catch (DAOException e) {
            logger.error("Error while UpdateAuc to DAO");
            throw new ServiceException("Error while UpdateAuc to DAO", e);
        }
    }

    @Override
    public Auction refreshAuction(String auctionId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        Auction auction = null;

        try {
            auction = userDAO.getAuctionInfo(auctionId);
            auction.setLots(userDAO.getLotsInfo(auctionId));
            for (Lot lot : auction.getLots()) {
                lot.setThings(userDAO.getThingsInfo(lot.getId()));
            }
        } catch (DAOException e) {
            logger.error("Error while refreshing auction from DAO");
            throw new ServiceException("Error while refreshing auction from DAO", e);
        }

        return auction;
    }

    @Override
    public void placeBet(String lotId, String currentPrice, String step, String buyer) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        String newCurrentPrice = String.valueOf(Integer.parseInt(currentPrice) + Integer.parseInt(step));

        try {
            userDAO.placeBet(lotId, newCurrentPrice, buyer);
        } catch (DAOException e) {
            logger.error("Error while placing bet into the DAO");
            throw new ServiceException("Error while placing bet into the DAO", e);
        }
    }

    @Override
    public void sellLot(String lotId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.sellLot(lotId);
        } catch (DAOException e) {
            logger.error("Error while selling lot into the DAO");
            throw new ServiceException("Error while selling lot into the DAO", e);
        }
    }
}
