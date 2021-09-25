package za.ac.nwu.ac.domain.exception;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException() {
        super("Incorrect password entered");
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
