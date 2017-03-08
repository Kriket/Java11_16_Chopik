package by.tc.parser.service;

import by.tc.parser.bean.ParserType;
import by.tc.parser.bean.WebApp;
import by.tc.parser.service.exception.ServiceException;

public interface ParserService {
    WebApp parse(String filePath, ParserType paserType) throws ServiceException;
}
