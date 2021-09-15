package goal;

import helper_classes.exception.InvalidGoalPointsException;

import java.time.LocalDate;

/**
 * Abstract parent class that represents a member's weekly goal
 * <code>DrivingGoal</code>, <code>SpendingGoal</code> and <code>HealthGoal</code> are the relevant child classes
 */

public class Goal {
    private int goalID;
    private String goalType;
    private int pointsNecessary;
    private int pointsEarned;
    private boolean goalAccomplished;
    private LocalDate startDate;

    /**
     * Constructor that creates a goal without any points being earned towards completing it and the current date as starting date
     * @param goalID mostly set to the goal ID in the database otherwise the default value is -1
     * @param goalType the type of goal that the member is working towards accomplishing
     * @param pointsNecessary the total amount of points that is necessary to accomplish the goal
     */

    public Goal(int goalID, String goalType, int pointsNecessary) {
        this.goalID = goalID;
        this.goalType = goalType;
        this.pointsNecessary = pointsNecessary;
        setPointsEarned(0);
        setGoalAccomplished(false);
        setStartDate(LocalDate.now());
    }

    /**
     * Constructor that creates a goal that already exists from the database
     * @param goalID mostly set to the goal ID in the database otherwise the default value is -1
     * @param goalType the type of goal that the member is working towards accomplishing
     * @param pointsNecessary the total amount of points that is necessary to accomplish the goal
     * @param pointsEarned the amount of points that a member has earned so far
     * @param goalAccomplished a boolean value indicating if the goal has been accomplished
     * @param startDate the date at which the member has started working towards their goal
     */

    public Goal(int goalID, String goalType, int pointsNecessary, int pointsEarned, boolean goalAccomplished, LocalDate startDate) {
        this.goalID = goalID;
        this.goalType = goalType;
        this.pointsNecessary = pointsNecessary;
        this.pointsEarned = pointsEarned;
        this.goalAccomplished = goalAccomplished;
        this.startDate = startDate;
    }

    /**
     * This adds points to the goal's points, if the points earned are the same as the goal points the goal is set to accomplished
     * @param points the amount of points that should be added to the goal's earned points
     * @throws InvalidGoalPointsException is thrown when the goal is already accomplished
     */

    public void addPoints(int points) throws InvalidGoalPointsException{
        if (goalAccomplished)
            throw new InvalidGoalPointsException("The goal has already been accomplished and points can not be added");
        else {
            setPointsEarned(Math.min(getTotalPoints(), getPointsEarned() + points));
            if (getPointsEarned() == getTotalPoints())
                setGoalAccomplished(true);
        }
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

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }
}
