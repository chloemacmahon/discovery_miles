package za.ac.nwu.ac.domain.dto.helper_classes.exception;

public class InvalidGoalPointsException extends RuntimeException{

    /**
     * This exception would typically be thrown if the za.ac.nwu.ac.domain.dto.member has already accomplished the za.ac.nwu.ac.domain.dto.goal and points cannot be added further
     * The default message is - Points cannot be added to za.ac.nwu.ac.domain.dto.goal
     */

    public InvalidGoalPointsException(){
        super("Points cannot be added to za.ac.nwu.ac.domain.dto.goal");
    }

    /**
     * This exception would typically be thrown if the za.ac.nwu.ac.domain.dto.member has already accomplished the za.ac.nwu.ac.domain.dto.goal and points cannot be added further
     * @param msg - The message that is displayed when the exception is thrown
     */

    public InvalidGoalPointsException(String msg){
        super(msg);
    }

}
