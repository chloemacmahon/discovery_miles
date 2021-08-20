package goal;

public class SpendingGoal extends Goal{

    public SpendingGoal(int totalPointsNeeded) {
        super(totalPointsNeeded);
    }

    public SpendingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished) {
        super(totalPointsNeeded, pointsEarned, goalAccomplished);
    }
}
