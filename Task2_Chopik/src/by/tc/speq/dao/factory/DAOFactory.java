package by.tc.speq.dao.factory;

import by.tc.speq.dao.EquipmentDAO;
import by.tc.speq.dao.UserDAO;
import by.tc.speq.dao.impl.SQLEquipmentDAO;
import by.tc.speq.dao.impl.SQLUserDAO;

/**
 * Created by User on 14.01.2017.
 */
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final EquipmentDAO sqlEquipment = new SQLEquipmentDAO();
    private final UserDAO sqlUser = new SQLUserDAO();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public EquipmentDAO getEquipmentDAO() {
        return sqlEquipment;
    }

    public UserDAO getUserDAO() {
        return sqlUser;
    }
}
