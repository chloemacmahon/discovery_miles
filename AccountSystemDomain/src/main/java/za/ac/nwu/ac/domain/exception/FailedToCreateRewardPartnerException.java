package za.ac.nwu.ac.domain.exception;

public class FailedToCreateRewardPartnerException extends RuntimeException{

    public FailedToCreateRewardPartnerException() {
        super("Could not create reward partner");
    }

    public FailedToCreateRewardPartnerException(String message) {
        super(message);
    }

    public FailedToCreateRewardPartnerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToCreateRewardPartnerException(Throwable cause) {
        super(cause);
    }

    public FailedToCreateRewardPartnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
