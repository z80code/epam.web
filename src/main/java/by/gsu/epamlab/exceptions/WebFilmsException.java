package by.gsu.epamlab.exceptions;

public class WebFilmsException extends RuntimeException {
    public WebFilmsException() {
    }

    public WebFilmsException(String message) {
        super(message);
    }

    public WebFilmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebFilmsException(Throwable cause) {
        super(cause);
    }

    public WebFilmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
