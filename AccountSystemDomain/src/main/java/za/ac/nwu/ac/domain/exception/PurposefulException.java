package za.ac.nwu.ac.domain.exception;

public class PurposefulException extends RuntimeException{
    public PurposefulException() {
    }

    public PurposefulException(String message) {
        super(message);
    }

    public PurposefulException(String message, Throwable cause) {
        super(message, cause);
    }

    public PurposefulException(Throwable cause) {
        super(cause);
    }

    public PurposefulException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
