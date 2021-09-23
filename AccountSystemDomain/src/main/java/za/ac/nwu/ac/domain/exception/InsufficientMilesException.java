package za.ac.nwu.ac.domain.exception;

public class InsufficientMilesException extends RuntimeException{

    /**
     * This exception would typically be thrown if the za.ac.nwu.ac.domain.dto.member does not have enough miles to purchase a za.ac.nwu.ac.domain.dto.reward
     * The default message is - Insufficient miles for this za.ac.nwu.ac.domain.dto.reward
     */

    public InsufficientMilesException(){
        super("Insufficient miles for this za.ac.nwu.ac.domain.dto.reward ");
    }

    /**
     * This exception would typically be thrown if the za.ac.nwu.ac.domain.dto.member does not have enough miles to purchase a za.ac.nwu.ac.domain.dto.reward
     * @param msg - The message that is displayed when the exception is thrown
     */

    public InsufficientMilesException(String msg){
        super(msg);
    }

}
