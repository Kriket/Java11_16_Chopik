package by.tc.speq.service;

import by.tc.speq.bean.SportEquipment;
import by.tc.speq.service.exception.ServiceException;

import java.util.Map;

/**
 * Created by User on 16.01.2017.
 */
public interface ShopService {

    Map<SportEquipment, Integer> getStockInfo() throws ServiceException;
    Map<SportEquipment, Integer> getRentInfo() throws ServiceException;
    void rentEquipment(SportEquipment sportEquipment) throws ServiceException;
    boolean isConsistEquipment(SportEquipment sportEquipment) throws ServiceException;

}
