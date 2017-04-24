package by.epam.webauction.exception;

/**
 * Created by User on 17.02.2017.
 */
public class ProjectEcxeption extends Exception {
    public static final long serialVersionUID = 1L;
    private Exception hiddenException;

    public ProjectEcxeption(String msg) {
        super(msg);
    }

    public ProjectEcxeption(String msg, Exception e) {
        super(msg);
        hiddenException = e;
    }

    public Exception getHiddenException() {
        return hiddenException;
    }
}
