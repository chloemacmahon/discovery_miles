package goal;

import java.time.LocalDate;

public class DrivingGoal extends Goal {

    public DrivingGoal(int totalPointsNeeded) {
        super(totalPointsNeeded);
    }

    public DrivingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate) {
        super(totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
