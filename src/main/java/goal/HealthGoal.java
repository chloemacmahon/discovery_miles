package goal;

import java.time.LocalDate;

public class HealthGoal extends Goal {

    public HealthGoal(int totalPointsNeeded, int goalID) {
        super(goalID, totalPointsNeeded);
    }

    public HealthGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
