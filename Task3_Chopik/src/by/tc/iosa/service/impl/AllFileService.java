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

    private static final char SLASH = '/';
    private static final char QUESTION_MARK = '?';
    private static final String PATTERN_FOR_TAG = "<[^>]+>";
    private static final String PATTERN_FOR_CONTENT = ">[^<]+<";

    private String data = null;
    private int offset = 0;
    private Pattern p = Pattern.compile(PATTERN_FOR_TAG);
    private Matcher m = null;
    private Pattern p1 = Pattern.compile(PATTERN_FOR_CONTENT);
    private Matcher m1 = null;
    boolean isContentNext = false;
    private String content = "";

    @Override
    public Node getNextNode() throws ServiceException {

        if (data == null) {
            return null;
        }

        if (m == null) {
            m = p.matcher(data);
            m1 = p1.matcher(data);
        }

        // when the next node is content
        if (isContentNext) {
            isContentNext = false;
            return new Node(content, NodeType.TAG_CONTENT);
        }

        // when the next node is tag
        if (m.find(offset)) {
            offset = m.end() - 1;

            if (isTitleTag()) {
                return new Node(data.substring(m.start() + 2, m.end() - 2), NodeType.TAG_WITHOUT_BODY);
            }

            if (isClosingTag()) {
                return new Node(data.substring(m.start() + 2, m.end() - 1), NodeType.CLOSING_TAG);
            }

            if (isTagWithoutBody()) {
                return new Node(data.substring(m.start() + 1, m.end() - 2), NodeType.TAG_WITHOUT_BODY);
            }

            Node result = new Node(data.substring(m.start() + 1, m.end() - 1), NodeType.OPENING_TAG);

            // Check is the next node is content
            checkNextContent();

            return result;

        }

        return null;

    }

    private boolean isTitleTag() {
        return data.charAt((m.start() + 1)) == QUESTION_MARK && data.charAt(m.end() - 2) == QUESTION_MARK;
    }

    private boolean isClosingTag() {
        return data.charAt((m.start() + 1)) == SLASH;
    }

    private boolean isTagWithoutBody() {
        return data.charAt((m.end() - 2)) == SLASH;
    }

    private void checkNextContent() {
        if (m1.find(offset) && m.find(offset)) {

            content = data.substring(m1.start() + 1, m1.end() - 1).trim();
            if ((m1.start() < m.start()) && (!content.isEmpty())) {
                isContentNext = true;
                offset = m1.end() - 1;
            }
        }
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
    public void initData() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            AllFileDAO allFileDAO = daoFactory.getAllFileDAO();
            data = allFileDAO.getAllData();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
