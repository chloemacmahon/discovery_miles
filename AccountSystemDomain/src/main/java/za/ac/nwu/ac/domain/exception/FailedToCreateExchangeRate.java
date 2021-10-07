package za.ac.nwu.ac.domain.exception;

public class FailedToCreateExchangeRate extends RuntimeException{

    public FailedToCreateExchangeRate() {
    }

    public FailedToCreateExchangeRate(String message) {
        super(message);
    }

    public FailedToCreateExchangeRate(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToCreateExchangeRate(Throwable cause) {
        super(cause);
    }

    public FailedToCreateExchangeRate(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
