package goal;

import java.time.LocalDate;

/**
 * Driving goal represents the member's weekly driving goal, this level of abstraction is used so that
 * if how a goal is accomplished is determined by the goal itself that the relevant changes can be made without structural changes being necessary
 */

public class DrivingGoal extends Goal {

    public DrivingGoal(int totalPointsNeeded, int goalID) {
        super(goalID, "Driving", totalPointsNeeded);
    }

    public DrivingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, "Driving", totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
