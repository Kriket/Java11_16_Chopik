package by.tc.parser.service.strategy.sax;

import by.tc.parser.bean.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserHandler extends DefaultHandler {

    public static final String WEB_APP_ATTR_ID      = "id";
    public static final String WEB_APP_ATTR_VERSION = "version";

    private WebApp webApp = null;

    private Filter          filter;
    private InitParam       initParam;
    private FilterMapping   filterMapping;
    private Servlet         servlet;
    private ServletMapping  servletMapping;
    private ErrorPage       errorPage;
    private TagName         tagName;
    private StringBuilder   text;

    @Override
    public void startDocument() throws SAXException {
        webApp = new WebApp();

        filter         = null;
        initParam      = null;
        filterMapping  = null;
        servlet        = null;
        servletMapping = null;
        errorPage      = null;

        tagName        = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        text = new StringBuilder();
        tagName = TagName.getTagByName(qName);
        switch (tagName) {
            case WEB_APP: {
                webApp.setId(attributes.getValue(WEB_APP_ATTR_ID));
                webApp.setVersion(attributes.getValue(WEB_APP_ATTR_VERSION));
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

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tagName = TagName.getTagByName(qName);
        switch (tagName) {
            case DISPLAY_NAME: {
                webApp.setDisplayName(text.toString());
                break;
            }
            case WELCOME_FILE: {
                webApp.addWelcomeFile(text.toString());
                break;
            }
            case FILTER_NAME: {
                if (filter != null) {
                    filter.setFilterName(text.toString());
                } else {
                    filterMapping.setFilterName(text.toString());
                }
                break;
            }
            case FILTER_CLASS: {
                if (filter != null) {
                    filter.setFilterClass(text.toString());
                }
                break;
            }
            case PARAM_NAME: {
                if (initParam != null) {
                    initParam.setParamName(text.toString());
                }
                break;
            }
            case PARAM_VALUE: {
                if (initParam != null) {
                    initParam.setParamValue(text.toString());
                }
                break;
            }
            case URL_PATTERN: {
                if (filterMapping != null) {
                    filterMapping.setUrlPattern(text.toString());
                } else {
                    servletMapping.setUrlPattern(text.toString());
                }
                break;
            }
            case DISPATCHER: {
                if (filterMapping != null) {
                    filterMapping.setDispatcher(text.toString());
                }
                break;
            }
            case LISTENER_CLASS: {
                webApp.addListenerClass(text.toString());
                break;
            }
            case SERVLET_NAME: {
                if (servlet != null) {
                    servlet.setServletName(text.toString());
                } else {
                    servletMapping.setServletName(text.toString());
                }
                break;
            }
            case SERVLET_CLASS: {
                if (servlet != null) {
                    servlet.setServletClass(text.toString());
                }
                break;
            }
            case EXCEPTION_TYPE: {
                if (errorPage != null) {
                    errorPage.setExceptionType(text.toString());
                }
                break;
            }
            case ERROR_CODE: {
                if (errorPage != null) {
                    errorPage.setErrorCode(Integer.parseInt(text.toString()));
                }
                break;
            }
            case LOCATION: {
                if (errorPage != null) {
                    errorPage.setLocation(text.toString());
                }
                break;
            }
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

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    public WebApp getWebApp() {
        return webApp;
    }
}
