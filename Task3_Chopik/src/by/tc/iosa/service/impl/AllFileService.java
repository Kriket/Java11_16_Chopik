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
    private Pattern p1 = Pattern.compile(">[^<]+<");
    private Matcher m1 = null;
    boolean isContentNext = false;
    private String content = "";

    @Override
    public Node getNextNode() throws ServiceException {
        Node result = new Node("", NodeType.TAG_WITHOUT_BODY);

        if (data == null) {
            return result;
        }

        if (m == null) {
            m = p.matcher(data);
            m1 = p1.matcher(data);
        }

        // when the next node is content
        if (isContentNext) {
            result.setContent(content);
            result.setNodeType(NodeType.TAG_CONTENT);
            isContentNext = false;
        } else if (m.find(offset)) {
            // when the next node is tag

            if (data.charAt((m.start() + 1)) == '?' && data.charAt(m.end() - 2) == '?') {
                result.setContent(data.substring(m.start() + 2, m.end() - 2));
                result.setNodeType(NodeType.TAG_WITHOUT_BODY);
                offset = m.end() - 1;
            } else if (data.charAt((m.start() + 1)) == '/') {
                result.setContent(data.substring(m.start() + 2, m.end() - 1));
                result.setNodeType(NodeType.CLOSING_TAG);
                offset = m.end() - 1;
            } else if (data.charAt((m.end() - 2)) == '/') {
                result.setContent(data.substring(m.start() + 1, m.end() - 2));
                result.setNodeType(NodeType.TAG_WITHOUT_BODY);
                offset = m.end() - 1;
            } else {

                result.setContent(data.substring(m.start() + 1, m.end() - 1));
                result.setNodeType(NodeType.OPENING_TAG);
                offset = m.end() - 1;

                // Check is the next node is content
                if (m1.find(offset) && m.find(offset)) {

                    content = data.substring(m1.start() + 1, m1.end() - 1).trim();
                    if ((m1.start() < m.start()) && (!content.isEmpty())) {
                        isContentNext = true;
                        offset = m1.end() - 1;
                    }
                }
            }

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
