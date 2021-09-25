package za.ac.nwu.ac.domain.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() {
        super("Password is not a strong enough password");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
