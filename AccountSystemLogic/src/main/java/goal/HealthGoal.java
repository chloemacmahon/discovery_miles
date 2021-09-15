package goal;

import java.time.LocalDate;

/**
 * Health goal represents the member's weekly health goal, this level of abstraction is used so that
 * if how a goal is accomplished is determined by the goal itself that the relevant changes can be made without structural changes being necessary
 */


public class HealthGoal extends Goal {

    public HealthGoal(int totalPointsNeeded, int goalID) {
        super(goalID, "Health", totalPointsNeeded);
    }

    public HealthGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, "Health", totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
