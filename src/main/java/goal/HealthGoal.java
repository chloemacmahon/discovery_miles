package goal;

public class HealthGoal extends Goal{

    public HealthGoal(int totalPointsNeeded) {
        super(totalPointsNeeded);
    }

    public HealthGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished) {
        super(totalPointsNeeded, pointsEarned, goalAccomplished);
    }
}
