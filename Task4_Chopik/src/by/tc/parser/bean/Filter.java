package by.tc.parser.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {

    private String filterName;
    private String filterClass;
    private ArrayList<InitParam> filterInitParam = new ArrayList<>();

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public void addParam(InitParam initParam) {
        this.filterInitParam.add(initParam);
    }

    public void addParam(String paramName, String paramValue) {
        this.addParam(new InitParam(paramName,paramValue));
    }

    public ArrayList<InitParam> getFilterInitParam() {
        return filterInitParam;
    }

}
