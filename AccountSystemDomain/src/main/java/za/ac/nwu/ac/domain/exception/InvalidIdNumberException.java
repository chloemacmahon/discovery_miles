package za.ac.nwu.ac.domain.exception;

public class InvalidIdNumberException extends RuntimeException{

    public InvalidIdNumberException() {
        super("ID number is not a valid South African ID number");
    }

    public InvalidIdNumberException(String message) {
        super(message);
    }
}
