package by.tc.parser.service.strategy.sax;

import by.tc.parser.bean.WebApp;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.strategy.ParserStrategy;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class SaxParserStrategy implements ParserStrategy {
    private XMLReader reader;
    private WebApp webApp = null;

    @Override
    public WebApp parse(String filePath) throws ServiceException {
        try {
            InputSource inputSource = new InputSource(new FileInputStream(filePath));
            reader = XMLReaderFactory.createXMLReader();
            SaxParserHandler saxParserHandler = new SaxParserHandler();
            reader.setContentHandler(saxParserHandler);
            reader.parse(inputSource);
            webApp = saxParserHandler.getWebApp();
        } catch (SAXException | IOException e) {
            throw new ServiceException("Exception while parsing", e);
        }

        return webApp;
    }
}
