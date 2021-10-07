package za.ac.nwu.ac.domain.exception;

public class FailedToFindExchangeRate extends RuntimeException{

    public FailedToFindExchangeRate() {
    }

    public FailedToFindExchangeRate(String message) {
        super(message);
    }

    public FailedToFindExchangeRate(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToFindExchangeRate(Throwable cause) {
        super(cause);
    }

    public FailedToFindExchangeRate(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
