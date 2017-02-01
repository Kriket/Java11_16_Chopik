package by.tc.iosa.service;

import by.tc.iosa.bean.Node;
import by.tc.iosa.service.exception.ServiceException;

/**
 * Created by User on 20.01.2017.
 */
public interface FileService {
    Node getNextNode() throws ServiceException;
    void initData() throws ServiceException;
}
