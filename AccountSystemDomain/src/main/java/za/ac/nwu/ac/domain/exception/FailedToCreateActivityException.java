package za.ac.nwu.ac.domain.exception;

public class FailedToCreateActivityException extends RuntimeException{
    public FailedToCreateActivityException() {
        super("Failed to create activity");
    }

    public FailedToCreateActivityException(String message) {
        super(message);
    }

    public FailedToCreateActivityException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToCreateActivityException(Throwable cause) {
        super(cause);
    }

    public FailedToCreateActivityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
