package by.tc.parser.service.strategy;

import by.tc.parser.bean.ParserType;
import by.tc.parser.bean.WebApp;
import by.tc.parser.service.exception.ServiceException;

public interface ParserStrategy {
    WebApp parse(String filePath) throws ServiceException;
}
