package by.tc.iosa.dao.factory;

import by.tc.iosa.dao.impl.AllFileDAO;
import by.tc.iosa.dao.impl.FileAnalyzerDAO;

/**
 * Created by User on 20.01.2017.
 */
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static final String FILE_PATH = "file.xml";

    //private FileAnalyzerDAO fileAnalyzer = new FileAnalyzerDAO(FILE_PATH);
    private AllFileDAO allFileDAO = new AllFileDAO(FILE_PATH);

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public AllFileDAO getAllFileDAO() {
        return allFileDAO;
    }

}
