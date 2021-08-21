package goal;

import java.time.LocalDate;

public class DrivingGoal extends Goal {

    public DrivingGoal(int totalPointsNeeded, int goalID) {
        super(goalID, totalPointsNeeded);
    }

    public DrivingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, int goalID) {
        super(goalID, totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
