package za.ac.nwu.ac.domain.dto.helper_classes.exception;

public class InvalidGameTileException extends RuntimeException{
    /**
     * This exception would typically be thrown if the game tile is not valid or does not exist or cannot be chosen
     * The default message is - The game tile is invalid
     */
    public InvalidGameTileException(){
        super("The game tile is invalid");
    }

    /**
     * This exception would typically be thrown if the game tile is not valid or does not exist or cannot be chosen
     * @param msg - The message that is displayed when the exception is thrown
     */
    public InvalidGameTileException(String msg){
        super(msg);
    }
}
