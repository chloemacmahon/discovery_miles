package goal;

public class DrivingGoal extends Goal{

    public DrivingGoal(int totalPointsNeeded) {
        super(totalPointsNeeded);
    }

    public DrivingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished) {
        super(totalPointsNeeded, pointsEarned, goalAccomplished);
    }
}
