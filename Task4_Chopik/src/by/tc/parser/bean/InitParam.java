package by.tc.parser.bean;

import java.util.HashMap;
import java.util.Map;

public class InitParam {

    private String paramName;
    private String paramValue;

    public InitParam() {
    }

    public InitParam(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
