package by.tc.iosa.dao.impl;

import by.tc.iosa.bean.Node;
import by.tc.iosa.dao.PortionedDAO;
import by.tc.iosa.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by User on 20.01.2017.
 */
public class FileAnalyzerDAO implements PortionedDAO {

    private String filePath;

    public FileAnalyzerDAO(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getDataPortion() throws DAOException {

        char[] mas = new char[1000];

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new DAOException(e);
        }

        return String.valueOf(mas);
    }
}
