package helper_classes.exception;

public class InvalidGameboardException extends RuntimeException{
    /**
     * This exception would typically be thrown if the set gameboard is not valid or does not exist
     * The default message is - The gameboard is invalid
     */
    public InvalidGameboardException(){
        super("The gameboard is invalid");
    }

    /**
     * This exception would typically be thrown if the set gameboard is not valid or does not exist
     * @param msg - The message that is displayed when the exception is thrown
     */
    public InvalidGameboardException(String msg){
        super(msg);
    }
}
