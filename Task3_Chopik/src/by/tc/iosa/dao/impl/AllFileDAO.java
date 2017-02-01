package by.tc.iosa.dao.impl;

import by.tc.iosa.dao.AllDataDAO;
import by.tc.iosa.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by User on 20.01.2017.
 */
public class AllFileDAO implements AllDataDAO {

    private static String filePath;

    public AllFileDAO(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        AllFileDAO.filePath = filePath;
    }

    @Override
    public String getAllData() throws DAOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new DAOException("ClothingBufferedReaderException", e);
                }
            }
        }

        return stringBuilder.toString();
    }
}
