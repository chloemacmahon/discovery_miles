package goal;

import java.time.LocalDate;

public class HealthGoal extends Goal {

    public HealthGoal(int totalPointsNeeded) {
        super(totalPointsNeeded);
    }

    public HealthGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate) {
        super(totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
