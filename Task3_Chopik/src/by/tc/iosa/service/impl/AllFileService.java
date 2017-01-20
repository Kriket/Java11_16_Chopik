package by.tc.iosa.service.impl;

import by.tc.iosa.bean.Node;
import by.tc.iosa.bean.NodeType;
import by.tc.iosa.dao.exception.DAOException;
import by.tc.iosa.dao.factory.DAOFactory;
import by.tc.iosa.dao.impl.AllFileDAO;
import by.tc.iosa.service.FileService;
import by.tc.iosa.service.exception.ServiceException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by User on 20.01.2017.
 */
public class AllFileService implements FileService {
    private String data = null;
    private int offset = 0;
    private Pattern p = Pattern.compile("<[^>]+>");
    private Matcher m = null;

    @Override
    public Node getNextNode() throws ServiceException {
        Node result = new Node("", NodeType.TAG_WITHOUT_BODY);

        if (data == null) {
            return result;
        }

        if (m == null) {
            m = p.matcher(data);
        }

        if (m.find(offset)) {

            String temp = data.substring(m.start(), m.end());
            String temp1 = data.substring(m.start()+1, m.end()-1);
            String temp2 = data.substring(m.start()+2, m.end()-2);
            char c = data.charAt(m.start() + 1);
            char c1 = data.charAt(m.end() - 2);

            // сравнение с символом доделать

            if (data.charAt((m.start() + 1)) == '?' && data.charAt(m.end() - 2) == '?') {
                result.setContent(data.substring(m.start() + 2, m.end() - 2));
                result.setNodeType(NodeType.TAG_WITHOUT_BODY);
                offset = m.end();
                return result;
            }

            if (data.charAt((m.start() + 1)) == '/') {
                result.setContent(data.substring(m.start() + 2, m.end() - 1));
                result.setNodeType(NodeType.CLOSING_TAG);
                offset = m.end();
                return result;
            }

            if (data.charAt((m.end() - 2)) == '/') {
                result.setContent(data.substring(m.start() + 1, m.end() - 2));
                result.setNodeType(NodeType.TAG_WITHOUT_BODY);
                offset = m.end();
                return result;
            }

            result.setContent(data.substring(m.start() + 1, m.end() - 1));
            result.setNodeType(NodeType.OPENING_TAG);
            offset = m.end();

        } else {
            result = null;
        }

        return result;
    }

    public void setFilePath(String path) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AllFileDAO allFileDAO = daoFactory.getAllFileDAO();
        allFileDAO.setFilePath(path);
    }

    public String getFilePath() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AllFileDAO allFileDAO = daoFactory.getAllFileDAO();
        return allFileDAO.getFilePath();
    }

    @Override
    public void getData() throws ServiceException {

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            AllFileDAO allFileDAO = daoFactory.getAllFileDAO();
            data = allFileDAO.getAllData();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }
}
