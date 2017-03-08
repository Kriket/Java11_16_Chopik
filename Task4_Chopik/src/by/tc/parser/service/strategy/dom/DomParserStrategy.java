package by.tc.parser.service.strategy.dom;

import by.tc.parser.bean.*;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.strategy.ParserStrategy;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

public class DomParserStrategy implements ParserStrategy {

    public static final String WEB_APP_ATTR_ID      = "id";
    public static final String WEB_APP_ATTR_VERSION = "version";

    private WebApp webApp = null;

    @Override
    public WebApp parse(String filePath) throws ServiceException {
        DOMParser parser = new DOMParser();
        try {
            parser.parse(filePath);
            Document document = parser.getDocument();
            Element root = document.getDocumentElement();

            webApp = new WebApp();

            webApp.setId(root.getAttribute(WEB_APP_ATTR_ID));
            webApp.setVersion(root.getAttribute(WEB_APP_ATTR_VERSION));

            webApp.setDisplayName(getChildTextContent(root, TagName.DISPLAY_NAME.toStringName()));

            fillWelcomeFileList(root);
            fillFilterList(root);
            fillFilterMappingList(root);
            fillListenerClassList(root);
            fillServletList(root);
            fillServletMappingList(root);
            fillErrorPageList(root);

        } catch (SAXException | IOException e) {
            throw new ServiceException("Exception while parsing", e);
        }

        return webApp;
    }

    private static Element getSingleChild(Element element, String childName) {
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element)nodeList.item(0);
        return child;
    }

    private static String getChildTextContent(Element element, String childName) {
        return getSingleChild(element, childName).getTextContent().trim();
    }

    private void fillWelcomeFileList(Element root) {
        NodeList welcomeFileList = root.getElementsByTagName(TagName.WELCOME_FILE.toStringName());
        for (int i = 0; i < welcomeFileList.getLength(); i++) {
            Element welcomeFileElement = (Element)welcomeFileList.item(i);
            webApp.addWelcomeFile(welcomeFileElement.getTextContent().trim());
        }
    }

    private void fillFilterList(Element root) {
        NodeList filterList = root.getElementsByTagName(TagName.FILTER.toStringName());
        Filter filter = null;
        for (int i = 0; i < filterList.getLength(); i++) {
            filter = new Filter();
            Element filterElement = (Element)filterList.item(i);

            filter.setFilterName(getChildTextContent(filterElement, TagName.FILTER_NAME.toStringName()));
            filter.setFilterClass(getChildTextContent(filterElement, TagName.FILTER_CLASS.toStringName()));

            fillInitParamList(filterElement, filter);

            webApp.addFilter(filter);
        }
    }

    private void fillInitParamList(Element parent, Object object) {

        NodeList initParamList = parent.getElementsByTagName(TagName.INIT_PARAM.toStringName());
        if (initParamList != null) {
            InitParam initParam = null;
            for (int j = 0; j < initParamList.getLength(); j++) {
                initParam = new InitParam();
                Element initParamElement = (Element)initParamList.item(j);

                initParam.setParamName(getChildTextContent(initParamElement, TagName.PARAM_NAME.toStringName()));
                initParam.setParamValue(getChildTextContent(initParamElement, TagName.PARAM_VALUE.toStringName()));

                if (object.getClass() == Filter.class) {
                    ((Filter)object).addParam(initParam);
                } else if (object.getClass() == Servlet.class) {
                    ((Servlet)object).addParam(initParam);
                }
            }
        }

    }

    private void fillFilterMappingList(Element root) {
        NodeList filterMappingList = root.getElementsByTagName(TagName.FILTER_MAPPING.toStringName());
        FilterMapping filterMapping = null;
        for (int i = 0; i < filterMappingList.getLength(); i++) {
            filterMapping = new FilterMapping();
            Element filterMappingElement = (Element)filterMappingList.item(i);

            filterMapping.setFilterName(getChildTextContent(filterMappingElement, TagName.FILTER_NAME.toStringName()));
            filterMapping.setUrlPattern(getChildTextContent(filterMappingElement, TagName.URL_PATTERN.toStringName()));
            filterMapping.setDispatcher(getChildTextContent(filterMappingElement, TagName.DISPATCHER.toStringName()));

            webApp.addFilterMapping(filterMapping);
        }
    }

    private void fillListenerClassList(Element root) {
        NodeList listenerClassList = root.getElementsByTagName(TagName.LISTENER_CLASS.toStringName());
        for (int i = 0; i < listenerClassList.getLength(); i++) {
            Element listenerClassElement = (Element)listenerClassList.item(i);
            webApp.addListenerClass(listenerClassElement.getTextContent().trim());
        }
    }

    private void fillServletList(Element root) {
        NodeList servletList = root.getElementsByTagName(TagName.SERVLET.toStringName());
        Servlet servlet = null;
        for (int i = 0; i < servletList.getLength(); i++) {
            servlet = new Servlet();
            Element servletElement = (Element)servletList.item(i);

            servlet.setServletName(getChildTextContent(servletElement, TagName.SERVLET_NAME.toStringName()));
            servlet.setServletClass(getChildTextContent(servletElement, TagName.SERVLET_CLASS.toStringName()));

            fillInitParamList(servletElement, servlet);

            webApp.addServlet(servlet);
        }
    }

    private void fillServletMappingList(Element root) {
        NodeList servletMappingList = root.getElementsByTagName(TagName.SERVLET_MAPPING.toStringName());
        ServletMapping servletMapping = null;
        for (int i = 0; i < servletMappingList.getLength(); i++) {
            servletMapping = new ServletMapping();
            Element servletMappingElement = (Element)servletMappingList.item(i);

            servletMapping.setServletName(getChildTextContent(servletMappingElement, TagName.SERVLET_NAME.toStringName()));
            servletMapping.setUrlPattern(getChildTextContent(servletMappingElement, TagName.URL_PATTERN.toStringName()));

            webApp.addServletMapping(servletMapping);
        }
    }

    private void fillErrorPageList(Element root) {
        NodeList errorPageList = root.getElementsByTagName(TagName.ERROR_PAGE.toStringName());
        ErrorPage errorPage = null;
        for (int i = 0; i < errorPageList.getLength(); i++) {
            errorPage = new ErrorPage();
            Element errorPageElement = (Element) errorPageList.item(i);

            if (getSingleChild(errorPageElement, TagName.ERROR_CODE.toStringName()) != null) {
                errorPage.setErrorCode(Integer.parseInt(getChildTextContent(errorPageElement, TagName.ERROR_CODE.toStringName())));
            }

            if (getSingleChild(errorPageElement, TagName.EXCEPTION_TYPE.toStringName()) != null) {
                errorPage.setExceptionType(getChildTextContent(errorPageElement, TagName.EXCEPTION_TYPE.toStringName()));
            }

            errorPage.setLocation(getChildTextContent(errorPageElement, TagName.LOCATION.toStringName()));

            webApp.addErrorPage(errorPage);
        }
    }
}
