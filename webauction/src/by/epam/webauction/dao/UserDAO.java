package by.epam.webauction.dao;

import by.epam.webauction.bean.Auction;
import by.epam.webauction.bean.Lot;
import by.epam.webauction.bean.Thing;
import by.epam.webauction.bean.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    User singIn(String login) throws DAOException;
    User registration(User user) throws DAOException;
    String addLot(String startPrice, String sellerId, String auctionId) throws DAOException;
    Boolean addAuction(String aucName, String step, String currency, String startdate) throws DAOException;
    Boolean deleteAuction(String auctionId) throws DAOException;
    Boolean deleteLots(String auctionId) throws DAOException;
    Boolean addThings(List<Thing> thingList) throws DAOException;
    ArrayList<String> UpdateAuc() throws DAOException;
    ArrayList<String> UpdateAuc(String sellerId) throws DAOException;
    Auction getAuctionInfo(String auctionId) throws DAOException;
    List<Lot> getLotsInfo(String auctionId) throws DAOException;
    List<Thing> getThingsInfo(String lotId) throws DAOException;
    void placeBet(String lotId, String currentPrice, String buyer) throws DAOException;
    void sellLot(String lotId) throws DAOException;
}
