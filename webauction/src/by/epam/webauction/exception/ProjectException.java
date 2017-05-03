package by.epam.webauction.exception;

public class ProjectException extends Exception {
    public static final long serialVersionUID = 1L;

    public ProjectException() {
        super();
    }

    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Exception e) {
        super(message, e);
    }

    public ProjectException(Exception e) {
        super(e);
    }
}
