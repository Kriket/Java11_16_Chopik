package by.tc.parser.bean;

public enum TagName {
    WEB_APP, DISPLAY_NAME,
    WELCOME_FILE_LIST, WELCOME_FILE,
    FILTER, FILTER_NAME, FILTER_CLASS,
    FILTER_MAPPING, URL_PATTERN, DISPATCHER,
    INIT_PARAM, PARAM_NAME, PARAM_VALUE,
    LISTENER, LISTENER_CLASS,
    SERVLET, SERVLET_NAME, SERVLET_CLASS, SERVLET_MAPPING,
    ERROR_PAGE, EXCEPTION_TYPE, ERROR_CODE, LOCATION;

    public String toStringName() {
        return this.name().toLowerCase().replace('_', '-');
    }

    public static TagName getTagByName(String tagName) {
        return TagName.valueOf(tagName.toUpperCase().replace('-', '_'));
    }
}
