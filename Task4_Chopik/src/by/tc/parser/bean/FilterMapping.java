package by.tc.parser.bean;

public class FilterMapping {

    private String filterName;
    private String urlPattern;
    private String dispatcher;

    public FilterMapping() {
    }

    public FilterMapping(String filterName, String urlPattern, String dispatcher) {
        this.filterName = filterName;
        this.urlPattern = urlPattern;
        this.dispatcher = dispatcher;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }
}
