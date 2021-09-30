package za.ac.nwu.ac.domain.exception;


public class FailedToLogInException extends RuntimeException{
    public FailedToLogInException() {
        super("Failed to log in to account");
    }

    public FailedToLogInException(String message) {
        super(message);
    }

    public FailedToLogInException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToLogInException(Throwable cause) {
        super(cause);
    }

    public FailedToLogInException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
