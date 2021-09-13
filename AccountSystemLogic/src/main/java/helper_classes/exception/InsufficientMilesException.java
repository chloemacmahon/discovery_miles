package helper_classes.exception;

public class InsufficientMilesException extends RuntimeException{

    /**
     * This exception would typically be thrown if the member does not have enough miles to purchase a reward
     * The default message is - Insufficient miles for this reward
     */

    public InsufficientMilesException(){
        super("Insufficient miles for this reward ");
    }

    /**
     * This exception would typically be thrown if the member does not have enough miles to purchase a reward
     * @param msg - The message that is displayed when the exception is thrown
     */

    public InsufficientMilesException(String msg){
        super(msg);
    }

}
