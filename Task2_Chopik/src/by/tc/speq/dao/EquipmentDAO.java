package by.tc.speq.dao;

import by.tc.speq.bean.SportEquipment;
import by.tc.speq.dao.exception.DAOException;

import java.util.Map;

/**
 * Created by User on 14.01.2017.
 */
public interface EquipmentDAO {

    Map<SportEquipment, Integer> getStockInfo()  throws DAOException;
    Map<SportEquipment, Integer> getRentInfo()  throws DAOException;
    void rentEquipment(SportEquipment sportEquipment)  throws DAOException;

}
