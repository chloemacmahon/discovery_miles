package goal;

import java.time.LocalDate;

public class SpendingGoal extends Goal {

    public SpendingGoal(int totalPointsNeeded) {
        super(totalPointsNeeded);
    }

    public SpendingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate) {
        super(totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
