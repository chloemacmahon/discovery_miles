package goal;

public class Goal {
    private int totalPointsNeeded;
    private int pointsEarned;
    private boolean goalAccomplished ;

    public Goal(int totalPointsNeeded){
        this.totalPointsNeeded = totalPointsNeeded;
        setPointsEarned(0);
        setGoalAccomplished(false);
    }

    public Goal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished) {
        this.totalPointsNeeded = totalPointsNeeded;
        this.pointsEarned = pointsEarned;
        this.goalAccomplished = goalAccomplished;
    }

    public void addPoints(int points){
        setPointsEarned(Math.min(getTotalPoints(),getPointsEarned()+points));
        if (getPointsEarned() == getTotalPoints())
            setGoalAccomplished(true);
    }

    public int getTotalPoints() {
        return totalPointsNeeded;
    }

    public void setTotalPoints(int totalPointsNeeded) {
        this.totalPointsNeeded = totalPointsNeeded;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public boolean isGoalAccomplished() {
        return goalAccomplished;
    }

    public void setGoalAccomplished(boolean goalAccomplished) {
        this.goalAccomplished = goalAccomplished;
    }
}
