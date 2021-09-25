package za.ac.nwu.ac.domain.exception;

public class FailedToCreateUserException extends RuntimeException{

    public FailedToCreateUserException() {
        super("Failed to Create User");
    }

    public FailedToCreateUserException(String message) {
        super(message);
    }
}
