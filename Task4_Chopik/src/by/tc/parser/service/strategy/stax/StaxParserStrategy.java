package by.tc.parser.service.strategy.stax;

import by.tc.parser.bean.*;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.strategy.ParserStrategy;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StaxParserStrategy implements ParserStrategy {

    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    private XMLStreamReader reader;

    public static final String WEB_APP_ATTR_ID      = "id";
    public static final String WEB_APP_ATTR_VERSION = "version";

    private WebApp webApp = null;

    private Filter          filter;
    private InitParam       initParam;
    private FilterMapping   filterMapping;
    private Servlet         servlet;
    private ServletMapping  servletMapping;
    private ErrorPage       errorPage;

    private TagName tagName;

    @Override
    public WebApp parse(String filePath) throws ServiceException {

        if (filePath == null || filePath.isEmpty()) {
            throw new ServiceException("Wrong path to the file");
        }

        try {
            InputStream inputStream = new FileInputStream(filePath);
            reader = inputFactory.createXMLStreamReader(inputStream);
            parsingProcess();
        } catch (FileNotFoundException e) {
            throw new ServiceException("Exception while creating FileInputStream", e);
        } catch (XMLStreamException e) {
            throw new ServiceException("Exception while creating XMLStreamReader", e);
        }

        return webApp;
    }

    private void parsingProcess() throws ServiceException {
        webApp = new WebApp();

        filter         = null;
        initParam      = null;
        filterMapping  = null;
        servlet        = null;
        servletMapping = null;
        errorPage      = null;

        tagName        = null;

        try {
            while (reader.hasNext()) {
                    int type = reader.next();
                    switch (type) {
                        case XMLStreamConstants.START_ELEMENT: {
                            startElement();
                            break;
                        }
                        case XMLStreamConstants.CHARACTERS: {
                            String text = reader.getText().trim();
                            if (!text.isEmpty()) {
                                characters(text);
                            }
                            break;
                        }
                        case XMLStreamConstants.END_ELEMENT: {
                            endElement();
                            break;
                        }
                    }
                }
        } catch (XMLStreamException e) {
            throw new ServiceException("Error during parsing", e);
        }
    }

    private void startElement() {
        tagName = TagName.getTagByName(reader.getLocalName());
        switch (tagName) {
            case WEB_APP: {
                webApp.setId(reader.getAttributeValue(null, WEB_APP_ATTR_ID));
                webApp.setVersion(reader.getAttributeValue(null, WEB_APP_ATTR_VERSION));
                break;
            }
            case FILTER: {
                filter = new Filter();
                break;
            }
            case INIT_PARAM: {
                initParam = new InitParam();
                break;
            }
            case FILTER_MAPPING: {
                filterMapping = new FilterMapping();
                break;
            }
            case SERVLET: {
                servlet = new Servlet();
                break;
            }
            case SERVLET_MAPPING: {
                servletMapping = new ServletMapping();
                break;
            }
            case ERROR_PAGE: {
                errorPage = new ErrorPage();
                break;
            }
        }
    }

    private void characters(String text) {
        switch (tagName) {
            case DISPLAY_NAME: {
                webApp.setDisplayName(text);
                break;
            }
            case WELCOME_FILE: {
                webApp.addWelcomeFile(text);
                break;
            }
            case FILTER_NAME: {
                if (filter != null) {
                    filter.setFilterName(text);
                } else {
                    filterMapping.setFilterName(text);
                }
                break;
            }
            case FILTER_CLASS: {
                if (filter != null) {
                    filter.setFilterClass(text);
                }
                break;
            }
            case PARAM_NAME: {
                if (initParam != null) {
                    initParam.setParamName(text);
                }
                break;
            }
            case PARAM_VALUE: {
                if (initParam != null) {
                    initParam.setParamValue(text);
                }
                break;
            }
            case URL_PATTERN: {
                if (filterMapping != null) {
                    filterMapping.setUrlPattern(text);
                } else {
                    servletMapping.setUrlPattern(text);
                }
                break;
            }
            case DISPATCHER: {
                if (filterMapping != null) {
                    filterMapping.setDispatcher(text);
                }
                break;
            }
            case LISTENER_CLASS: {
                webApp.addListenerClass(text);
                break;
            }
            case SERVLET_NAME: {
                if (servlet != null) {
                    servlet.setServletName(text);
                } else {
                    servletMapping.setServletName(text);
                }
                break;
            }
            case SERVLET_CLASS: {
                if (servlet != null) {
                    servlet.setServletClass(text);
                }
                break;
            }
            case EXCEPTION_TYPE: {
                if (errorPage != null) {
                    errorPage.setExceptionType(text);
                }
                break;
            }
            case ERROR_CODE: {
                if (errorPage != null) {
                    errorPage.setErrorCode(Integer.parseInt(text));
                }
                break;
            }
            case LOCATION: {
                if (errorPage != null) {
                    errorPage.setLocation(text);
                }
                break;
            }
        }
    }

    private void endElement() {
        tagName = TagName.getTagByName(reader.getLocalName());
        switch (tagName) {
            case INIT_PARAM: {
                if (filter != null) {
                    filter.addParam(initParam);
                } else {
                    servlet.addParam(initParam);
                }
                initParam = null;
                break;
            }
            case FILTER: {
                webApp.addFilter(filter);
                filter = null;
                break;
            }
            case FILTER_MAPPING: {
                webApp.addFilterMapping(filterMapping);
                filterMapping = null;
                break;
            }
            case SERVLET: {
                webApp.addServlet(servlet);
                servlet = null;
                break;
            }
            case SERVLET_MAPPING: {
                webApp.addServletMapping(servletMapping);
                servletMapping = null;
                break;
            }
            case ERROR_PAGE: {
                webApp.addErrorPage(errorPage);
                errorPage = null;
                break;
            }
        }
    }
}
