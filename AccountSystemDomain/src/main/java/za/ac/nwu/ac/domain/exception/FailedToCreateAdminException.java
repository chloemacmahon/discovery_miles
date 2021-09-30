package za.ac.nwu.ac.domain.exception;

public class FailedToCreateAdminException extends RuntimeException{

    public FailedToCreateAdminException() {
        super("Failed to create admin account");
    }

    public FailedToCreateAdminException(String message) {
        super(message);
    }

    public FailedToCreateAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToCreateAdminException(Throwable cause) {
        super(cause);
    }

    public FailedToCreateAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
