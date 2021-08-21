package goal;

import java.time.LocalDate;

public class SpendingGoal extends Goal {

    public SpendingGoal(int totalPointsNeeded, int goalID) {
        super(goalID, totalPointsNeeded);
    }

    public SpendingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
