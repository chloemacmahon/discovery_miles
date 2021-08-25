package goal;

import java.time.LocalDate;

public class HealthGoal extends Goal {

    public HealthGoal(int totalPointsNeeded, int goalID) {
        super(goalID, "Health", totalPointsNeeded);
    }

    public HealthGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, "Health", totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
