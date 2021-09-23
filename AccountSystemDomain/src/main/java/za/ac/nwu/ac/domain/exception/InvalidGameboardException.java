package za.ac.nwu.ac.domain.exception;

public class InvalidGameboardException extends RuntimeException{
    /**
     * This exception would typically be thrown if the set za.ac.nwu.ac.domain.dto.gameboard is not valid or does not exist
     * The default message is - The za.ac.nwu.ac.domain.dto.gameboard is invalid
     */
    public InvalidGameboardException(){
        super("The za.ac.nwu.ac.domain.dto.gameboard is invalid");
    }

    /**
     * This exception would typically be thrown if the set za.ac.nwu.ac.domain.dto.gameboard is not valid or does not exist
     * @param msg - The message that is displayed when the exception is thrown
     */
    public InvalidGameboardException(String msg){
        super(msg);
    }
}
