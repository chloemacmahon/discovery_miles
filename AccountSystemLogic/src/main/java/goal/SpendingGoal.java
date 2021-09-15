package goal;

import java.time.LocalDate;

/**
 * Spending goal represents the member's weekly spending goal, this level of abstraction is used so that
 * if how a goal is accomplished is determined by the goal itself that the relevant changes can be made without structural changes being necessary
 */

public class SpendingGoal extends Goal {

    public SpendingGoal(int totalPointsNeeded, int goalID) {
        super(goalID, "Spending", totalPointsNeeded);
    }

    public SpendingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, "Spending", totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
