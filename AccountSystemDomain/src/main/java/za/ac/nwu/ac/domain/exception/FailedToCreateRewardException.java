package za.ac.nwu.ac.domain.exception;

public class FailedToCreateRewardException extends RuntimeException{

    public FailedToCreateRewardException() {
        super("Could not create reward");
    }

    public FailedToCreateRewardException(String message) {
        super(message);
    }

    public FailedToCreateRewardException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToCreateRewardException(Throwable cause) {
        super(cause);
    }

    public FailedToCreateRewardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
