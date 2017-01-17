package by.tc.speq.service.impl;

import by.tc.speq.bean.SportEquipment;
import by.tc.speq.dao.EquipmentDAO;
import by.tc.speq.dao.exception.DAOException;
import by.tc.speq.dao.factory.DAOFactory;
import by.tc.speq.service.exception.ServiceException;
import by.tc.speq.service.ShopService;

import java.util.Map;

/**
 * Created by User on 16.01.2017.
 */
public class ShopServiceImpl implements ShopService {

    @Override
    public Map<SportEquipment, Integer> getStockInfo() throws ServiceException {

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            EquipmentDAO equipmentDAO = daoFactory.getEquipmentDAO();

            return equipmentDAO.getStockInfo();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<SportEquipment, Integer> getRentInfo() throws ServiceException {

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            EquipmentDAO equipmentDAO = daoFactory.getEquipmentDAO();

            return equipmentDAO.getRentInfo();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void rentEquipment(SportEquipment sportEquipment) throws ServiceException {

        // проверяем параметры
        if (sportEquipment.getTitle() == null || sportEquipment.getTitle().isEmpty()) {
            throw new ServiceException("Incorrect title");
        }

        if (sportEquipment.getCategory() == null) {
            throw new ServiceException("Incorrect category");
        }

        if (sportEquipment.getPrice() < 0) {
            throw new ServiceException("Incorrect price");
        }

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            EquipmentDAO equipmentDAO = daoFactory.getEquipmentDAO();
            equipmentDAO.rentEquipment(sportEquipment);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean isConsistEquipment(SportEquipment sportEquipment) throws ServiceException {

        // проверяем параметры
        if (sportEquipment.getTitle() == null || sportEquipment.getTitle().isEmpty()) {
            throw new ServiceException("Incorrect title");
        }

        if (sportEquipment.getCategory() == null) {
            throw new ServiceException("Incorrect category");
        }

        if (sportEquipment.getPrice() < 0) {
            throw new ServiceException("Incorrect price");
        }

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            EquipmentDAO equipmentDAO = daoFactory.getEquipmentDAO();

            Map<SportEquipment, Integer> equipmentList = equipmentDAO.getStockInfo();

            for (Map.Entry<SportEquipment, Integer> pair : equipmentList.entrySet()) {
                if (sportEquipment.equals(pair.getKey())) {
                    if (pair.getValue() != 0) {
                        return true;
                    }
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return false;
    }
}
