package goal;

import java.time.LocalDate;

public class SpendingGoal extends Goal {

    public SpendingGoal(int totalPointsNeeded, int goalID) {
        super(goalID, "Spending", totalPointsNeeded);
    }

    public SpendingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, "Spending", totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
