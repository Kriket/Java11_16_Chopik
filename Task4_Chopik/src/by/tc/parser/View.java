package by.tc.parser;

import by.tc.parser.bean.*;
import by.tc.parser.service.ParserService;
import by.tc.parser.service.exception.ServiceException;
import by.tc.parser.service.impl.ParserServiceImpl;

public class View {

    public static final String filePath = "src/by/tc/parser/resources/web.xml";

    public static void main(String[] args) {
        ParserService service = new ParserServiceImpl();
        try {
            WebApp webApp = service.parse(filePath, ParserType.STAX);
            //WebApp webApp = service.parse(filePath, ParserType.SAX);
            //WebApp webApp = service.parse(filePath, ParserType.DOM);

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Web APP");
            stringBuilder.append(System.lineSeparator());

            stringBuilder.append("      id: ").append(webApp.getId());
            stringBuilder.append(System.lineSeparator());
            
            stringBuilder.append("      version: ").append(webApp.getVersion());
            stringBuilder.append(System.lineSeparator());

            stringBuilder.append("      display name: ").append(webApp.getDisplayName());
            stringBuilder.append(System.lineSeparator());

            stringBuilder.append("WelcomeFileList:");
            stringBuilder.append(System.lineSeparator());
            
            for (String str : webApp.getWelcomeFileList()) {
                stringBuilder.append("      ").append(str);
                stringBuilder.append(System.lineSeparator());
            }

            stringBuilder.append("FilterList");
            stringBuilder.append(System.lineSeparator());

            for (Filter filter : webApp.getFilterList()) {
                stringBuilder.append("  ").append(filter.getFilterName());
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append("  ").append(filter.getFilterClass());
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append("  init params:");
                stringBuilder.append(System.lineSeparator());

                for (InitParam initParam : filter.getFilterInitParam()) {
                    stringBuilder.append("      ").append(initParam.getParamName());
                    stringBuilder.append(System.lineSeparator());
                    stringBuilder.append("      ").append(initParam.getParamValue());
                    stringBuilder.append(System.lineSeparator());
                }
            }

            stringBuilder.append("FilterMapingList");
            stringBuilder.append(System.lineSeparator());
            
            for (FilterMapping filterMapping : webApp.getFilterMappingList()) {
                stringBuilder.append("      ").append(filterMapping.getFilterName());
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append("      ").append(filterMapping.getUrlPattern());
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append("      ").append(filterMapping.getDispatcher());
                stringBuilder.append(System.lineSeparator());
            }

            stringBuilder.append("Listeners");
            stringBuilder.append(System.lineSeparator());
            
            for (String listener : webApp.getListenerClassList()) {
                stringBuilder.append("      ").append(listener);
                stringBuilder.append(System.lineSeparator());
            }

            stringBuilder.append("ServletList");
            stringBuilder.append(System.lineSeparator());

            for (Servlet servlet : webApp.getServletList()) {
                stringBuilder.append("  ").append(servlet.getServletName());
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append("  ").append(servlet.getServletClass());
                stringBuilder.append(System.lineSeparator());

                if (servlet.getServletInitParam().size() > 0) {
                    stringBuilder.append("  init params:");
                    stringBuilder.append(System.lineSeparator());

                    for (InitParam initParam : servlet.getServletInitParam()) {
                        stringBuilder.append("      ").append(initParam.getParamName());
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("      ").append(initParam.getParamValue());
                        stringBuilder.append(System.lineSeparator());
                    }
                }
            }

            stringBuilder.append("ServletMappingList");
            stringBuilder.append(System.lineSeparator());

            for (ServletMapping servletMapping : webApp.getServletMappingList()) {
                stringBuilder.append("      ").append(servletMapping.getServletName());
                stringBuilder.append(System.lineSeparator());
                stringBuilder.append("      ").append(servletMapping.getUrlPattern());
                stringBuilder.append(System.lineSeparator());
            }

            stringBuilder.append("ErrorPages");
            stringBuilder.append(System.lineSeparator());
            
            for (ErrorPage errorPage : webApp.getErrorPageList()) {
                if (errorPage.getErrorCode() != 0) {
                    stringBuilder.append("      ").append(errorPage.getErrorCode());
                    stringBuilder.append(System.lineSeparator());
                }

                if (errorPage.getExceptionType() != null) {
                    stringBuilder.append("      ").append(errorPage.getExceptionType());
                    stringBuilder.append(System.lineSeparator());
                }
                stringBuilder.append("      ").append(errorPage.getLocation());
                stringBuilder.append(System.lineSeparator());
            }

            System.out.println(stringBuilder.toString());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
