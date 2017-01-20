package by.tc.iosa.dao;

import by.tc.iosa.bean.Node;
import by.tc.iosa.dao.exception.DAOException;

import java.io.FileNotFoundException;

/**
 * Created by User on 20.01.2017.
 */
public interface PortionedDAO {

    String getDataPortion() throws DAOException;

}
