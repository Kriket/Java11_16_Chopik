package by.tc.parser.bean;

public class ServletMapping {

    private String servletName;
    private String urlPattern;

    public ServletMapping() {
    }

    public ServletMapping(String servletName, String urlPattern) {
        this.servletName = servletName;
        this.urlPattern = urlPattern;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
}
