package za.ac.nwu.ac.domain.dto.goal;

import za.ac.nwu.ac.domain.exception.InvalidGoalPointsException;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Abstract parent class that represents a za.ac.nwu.ac.domain.dto.member's weekly za.ac.nwu.ac.domain.dto.goal
 * <code>DrivingGoal</code>, <code>SpendingGoal</code> and <code>HealthGoal</code> are the relevant child classes
 */
@Data
@Entity
@Component
public class Goal {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long goalID;

    @Column(name = "type",nullable = false, length = 10)
    private String goalType;

    @Column(name = "points_necessary")
    private int pointsNecessary;

    @Column(name = "points_earned")
    private int pointsEarned;

    @Column(name = "goal_accomplished")
    private boolean goalAccomplished;

    @Column(name = "start_date")
    private LocalDate startDate;

    //@JoinColumn(referencedColumnName =  "za.ac.nwu.ac.domain.dto.member.member_id",name = "goal.member_id")
    //@ManyToMany
    private Long memberId;

    public Goal(){

    }

    /**
     * Constructor that creates a za.ac.nwu.ac.domain.dto.goal without any points being earned towards completing it and the current date as starting date
     * @param goalType the type of za.ac.nwu.ac.domain.dto.goal that the za.ac.nwu.ac.domain.dto.member is working towards accomplishing
     * @param pointsNecessary the total amount of points that is necessary to accomplish the za.ac.nwu.ac.domain.dto.goal
     */

    public Goal(String goalType, int pointsNecessary, Long memberId) {
        this.goalType = goalType;
        this.pointsNecessary = pointsNecessary;
        this.memberId = memberId;
        setPointsEarned(0);
        setGoalAccomplished(false);
        setStartDate(LocalDate.now());
    }

    /**
     * Constructor that creates a za.ac.nwu.ac.domain.dto.goal that already exists from the database
     * @param goalType the type of za.ac.nwu.ac.domain.dto.goal that the za.ac.nwu.ac.domain.dto.member is working towards accomplishing
     * @param pointsNecessary the total amount of points that is necessary to accomplish the za.ac.nwu.ac.domain.dto.goal
     * @param pointsEarned the amount of points that a za.ac.nwu.ac.domain.dto.member has earned so far
     * @param goalAccomplished a boolean value indicating if the za.ac.nwu.ac.domain.dto.goal has been accomplished
     * @param startDate the date at which the za.ac.nwu.ac.domain.dto.member has started working towards their za.ac.nwu.ac.domain.dto.goal
     */

    public Goal(String goalType, int pointsNecessary, int pointsEarned, boolean goalAccomplished, LocalDate startDate, Long memberId) {
        this.goalType = goalType;
        this.pointsNecessary = pointsNecessary;
        this.pointsEarned = pointsEarned;
        this.goalAccomplished = goalAccomplished;
        this.startDate = startDate;
        this.memberId = memberId;
    }

    /**
     * This adds points to the za.ac.nwu.ac.domain.dto.goal's points, if the points earned are the same as the za.ac.nwu.ac.domain.dto.goal points the za.ac.nwu.ac.domain.dto.goal is set to accomplished
     * @param points the amount of points that should be added to the za.ac.nwu.ac.domain.dto.goal's earned points
     * @throws InvalidGoalPointsException is thrown when the za.ac.nwu.ac.domain.dto.goal is already accomplished
     */

    public void addPoints(int points) throws InvalidGoalPointsException{
        if (goalAccomplished)
            throw new InvalidGoalPointsException("The goal has already been accomplished and points can not be added");
        else {
            setPointsEarned(Math.min(this.pointsNecessary, getPointsEarned() + points));
            if (getPointsEarned() == this.pointsNecessary)
                setGoalAccomplished(true);
        }
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
