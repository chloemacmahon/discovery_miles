package goal;

import java.time.LocalDate;

public class Goal {
    private int goalID;
    private int pointsNecessary;
    private int pointsEarned;
    private boolean goalAccomplished;
    private LocalDate startDate;

    public Goal(int goalID, int pointsNecessary) {
        this.goalID = goalID;
        this.pointsNecessary = pointsNecessary;
        setPointsEarned(0);
        setGoalAccomplished(false);
        setStartDate(LocalDate.now());
    }

    public Goal(int goalID, int pointsNecessary, int pointsEarned, boolean goalAccomplished, LocalDate startDate) {
        this.goalID = goalID;
        this.pointsNecessary = pointsNecessary;
        this.pointsEarned = pointsEarned;
        this.goalAccomplished = goalAccomplished;
        this.startDate = startDate;
    }

    public void addPoints(int points) {
        setPointsEarned(Math.min(getTotalPoints(), getPointsEarned() + points));
        if (getPointsEarned() == getTotalPoints())
            setGoalAccomplished(true);
    }

    public int getTotalPoints() {
        return pointsNecessary;
    }

    public void setTotalPoints(int totalPointsNeeded) {
        this.pointsNecessary = totalPointsNeeded;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalID=" + goalID +
                ", pointsNecessary=" + pointsNecessary +
                ", pointsEarned=" + pointsEarned +
                ", goalAccomplished=" + goalAccomplished +
                ", startDate=" + startDate +
                '}';
    }
}
