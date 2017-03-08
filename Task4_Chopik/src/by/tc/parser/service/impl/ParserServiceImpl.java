package by.tc.parser.service.impl;

import by.tc.parser.bean.ParserType;
import by.tc.parser.bean.WebApp;
import by.tc.parser.service.ParserService;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.strategy.ParserStrategy;
import by.tc.parser.service.strategy.dom.DomParserStrategy;
import by.tc.parser.service.strategy.sax.SaxParserStrategy;
import by.tc.parser.service.strategy.stax.StaxParserStrategy;

public class ParserServiceImpl implements ParserService {

    private ParserStrategy strategy;

    @Override
    public WebApp parse(String filePath, ParserType paserType) throws ServiceException {
         switch (paserType) {
             case DOM:
                 strategy = new DomParserStrategy();
                 break;
             case SAX:
                 strategy = new SaxParserStrategy();
                 break;
             case STAX:
                 strategy = new StaxParserStrategy();
                 break;
             default:
                 throw new ServiceException("Parser not found");
         }

         return strategy.parse(filePath);
    }
}
