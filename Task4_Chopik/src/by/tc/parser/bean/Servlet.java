package by.tc.parser.bean;

import java.util.ArrayList;

public class Servlet {

    private String servletName;
    private String servletClass;
    private ArrayList<InitParam> servletInitParam = new ArrayList<>();

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public void addParam(InitParam initParam) {
        this.servletInitParam.add(initParam);
    }

    public void addParam(String paramName, String paramValue) {
        addParam(new InitParam(paramName, paramValue));
    }

    public ArrayList<InitParam> getServletInitParam() {
        return servletInitParam;
    }
}
