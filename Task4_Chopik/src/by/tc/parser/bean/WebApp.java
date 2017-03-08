package by.tc.parser.bean;

import java.util.ArrayList;
import java.util.List;

public class WebApp {

    private String id;
    private String version;

    private String               displayName;
    private List<String>         welcomeFileList;
    private List<Filter>         filterList;
    private List<FilterMapping>  filterMappingList;
    private List<String>         listenerClassList;
    private List<Servlet>        servletList;
    private List<ServletMapping> servletMappingList;
    private List<ErrorPage>      errorPageList;

    public WebApp() {
        welcomeFileList     = new ArrayList<>();
        filterList          = new ArrayList<>();
        filterMappingList   = new ArrayList<>();
        listenerClassList   = new ArrayList<>();
        servletList         = new ArrayList<>();
        servletMappingList  = new ArrayList<>();
        errorPageList       = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void addWelcomeFile(String welcomeFile) {
        welcomeFileList.add(welcomeFile);
    }

    public List<String> getWelcomeFileList() {
        return welcomeFileList;
    }

    public void setWelcomeFileList(List<String> welcomeFileList) {
        this.welcomeFileList = welcomeFileList;
    }

    public void addFilter(Filter filter) {
        filterList.add(filter);
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public void addFilterMapping(FilterMapping filterMapping) {
        this.filterMappingList.add(filterMapping);
    }

    public List<FilterMapping> getFilterMappingList() {
        return filterMappingList;
    }

    public void addListenerClass(String listener) {
        listenerClassList.add(listener);
    }

    public List<String> getListenerClassList() {
        return listenerClassList;
    }

    public void setListenerClassList(List<String> listenerClassList) {
        this.listenerClassList = listenerClassList;
    }

    public void addServlet(Servlet servlet) {
        servletList.add(servlet);
    }

    public List<Servlet> getServletList() {
        return servletList;
    }

    public void setServletList(List<Servlet> servletList) {
        this.servletList = servletList;
    }

    public void addServletMapping(ServletMapping servletMapping) {
        this.servletMappingList.add(servletMapping);
    }

    public List<ServletMapping> getServletMappingList() {
        return servletMappingList;
    }

    public void addErrorPage(ErrorPage errorPage) {
        errorPageList.add(errorPage);
    }

    public List<ErrorPage> getErrorPageList() {
        return errorPageList;
    }

    public void setErrorPageList(List<ErrorPage> errorPageList) {
        this.errorPageList = errorPageList;
    }
}
