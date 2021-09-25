package za.ac.nwu.ac.domain.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("Email address provided is not a valid email address");
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
