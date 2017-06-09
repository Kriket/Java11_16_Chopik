package by.epam.webauction.service;

import by.epam.webauction.bean.Auction;
import by.epam.webauction.bean.Thing;
import by.epam.webauction.bean.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    User singUp(String nickname, String password, String email) throws ServiceException;
    User singIn(String email, String password) throws ServiceException;
    String addLot(String startPrice, String sellerId, String auctionId) throws ServiceException;
    Boolean addAuction(String aucName, String step, String currency, String startdate) throws ServiceException;
    Boolean deleteAuction(String auctionId) throws ServiceException;
    Boolean deleteLots(String auctionId) throws ServiceException;
    Boolean addThings(List<Thing> thingList) throws ServiceException;
    ArrayList<String> UpdateAuc() throws ServiceException;
    ArrayList<String> UpdateAuc(String sellerId) throws ServiceException;
    Auction refreshAuction(String auctionId) throws ServiceException;
    void placeBet(String lotId, String currentPrice, String step, String buyer) throws ServiceException;
    void sellLot(String lotId) throws ServiceException;
}
