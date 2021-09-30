package za.ac.nwu.ac.domain.dto.member;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.gameboard.GameBoard;
import za.ac.nwu.ac.domain.dto.goal.DrivingGoal;
import za.ac.nwu.ac.domain.dto.goal.Goal;
import za.ac.nwu.ac.domain.dto.goal.HealthGoal;
import za.ac.nwu.ac.domain.dto.goal.SpendingGoal;
import za.ac.nwu.ac.domain.dto.reward.Reward;
import za.ac.nwu.ac.domain.exception.InsufficientMilesException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Component
public class Member {
    private static int defaultGoalPoints = 600;

    public static int getDefaultGoalPoints() {
        return defaultGoalPoints;
    }

    public static void setDefaultGoalPoints(int defaultGoalPoints) {
        Member.defaultGoalPoints = defaultGoalPoints;
    }

    /**
     * This class represents a discovery vitality za.ac.nwu.ac.domain.dto.member
     */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long memberId;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String password;
    @Column(name = "id_number", unique = true, nullable = false)
    private String idNumber;
    @Column(unique = true, nullable = false)
    private String email;
    @Column
    private int miles;
    @OneToOne(cascade = {CascadeType.ALL})
    private GameBoard gameBoard;
    @OneToOne(cascade = {CascadeType.ALL})
    private HealthGoal healthGoal;
    @OneToOne(cascade = {CascadeType.ALL})
    private DrivingGoal drivingGoal;
    @OneToOne(cascade = {CascadeType.ALL})
    private SpendingGoal spendingGoal;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Reward> rewards;

    public Member() {
    }

    /**
     * Only personal details are received as parameters
     *
     * @param name     Member name
     * @param surname  Member surname
     * @param password Member account password
     * @param idNumber Members's ID number
     * @param email    Member's email address
     */

    public Member(String name, String surname, String password, String idNumber, String email) {
        this(name, surname, password, idNumber, email, 0);
    }

    /**
     * Personal details and miles are received as parameters
     *
     * @param name     Member name
     * @param surname  Member surname
     * @param password Member account password
     * @param idNumber Members's ID number
     * @param email    Member's email address
     * @param miles    The za.ac.nwu.ac.domain.dto.member's available miles
     */

    public Member(String name, String surname, String password, String idNumber, String email, int miles) {
        this(name, surname, password, idNumber, email, miles, new ArrayList<>(), new ArrayList<>());
        setGoalsFromList(createDefaultWeeklyGoals());
    }

    /**
     * Personal details, miles, rewards and goals are received as parameters
     *
     * @param name     Member name
     * @param surname  Member surname
     * @param password Member account password
     * @param idNumber Members's ID number
     * @param email    Member's email address
     * @param miles    The za.ac.nwu.ac.domain.dto.member's available miles
     * @param goals    The za.ac.nwu.ac.domain.dto.member's weekly goals
     * @param rewards  The rewards that the za.ac.nwu.ac.domain.dto.member has accumulated thus far
     */
    public Member(String name, String surname, String password, String idNumber, String email, int miles, List<Goal> goals, List<Reward> rewards) {
        this(name, surname, password, idNumber, email, miles, new GameBoard(), goals, rewards);
    }

    /**
     * Receives all variables
     *
     * @param name      Member name
     * @param surname   Member surname
     * @param password  Member account password
     * @param idNumber  Members's ID number
     * @param email     Member's email address
     * @param miles     The za.ac.nwu.ac.domain.dto.member's available miles
     * @param gameBoard The game board that has been generated for the za.ac.nwu.ac.domain.dto.member
     * @param goals     The za.ac.nwu.ac.domain.dto.member's weekly goals
     * @param rewards   The rewards that the za.ac.nwu.ac.domain.dto.member has accumulated thus far
     */

    public Member(String name, String surname, String password, String idNumber, String email, int miles, GameBoard gameBoard, List<Goal> goals, List<Reward> rewards) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.idNumber = idNumber;
        this.email = email;
        this.miles = miles;
        this.gameBoard = gameBoard;
        setGoalsFromList(goals);
        this.rewards = rewards;
    }

    /**
     * Adds miles to the za.ac.nwu.ac.domain.dto.member's available miles
     *
     * @param miles miles to be added to the available miles
     */

    public void addMiles(int miles) {
        setMiles(getMiles() + miles);
    }

    /**
     * Subtract miles from the za.ac.nwu.ac.domain.dto.member's available miles
     *
     * @param miles reduces miles if there are miles available
     * @throws InsufficientMilesException throws the exception if there is not enough miles available
     */

    public void subtractMiles(int miles) throws InsufficientMilesException {
        if (getMiles() - miles < 0)
            throw new InsufficientMilesException("Not enough miles available");
        else
            setMiles(getMiles() - miles);
    }

    /**
     * Adds za.ac.nwu.ac.domain.dto.reward to the user za.ac.nwu.ac.domain.dto.reward list
     * Is called by <code>chooseReward</code>
     *
     * @param reward the za.ac.nwu.ac.domain.dto.reward that the za.ac.nwu.ac.domain.dto.member has chosen to add to their za.ac.nwu.ac.domain.dto.reward
     */

    public void addReward(Reward reward) {
        getRewards().add(reward);
    }

    /**
     * Creates weekly goals for the za.ac.nwu.ac.domain.dto.member with the points they need to collect to accomplish the za.ac.nwu.ac.domain.dto.goal
     * the start date of the za.ac.nwu.ac.domain.dto.goal will be set to the current date
     * Uses the static point necessary to complete goal amount
     */

    private List<Goal> createDefaultWeeklyGoals() {
        List<Goal> goals = new ArrayList<>();
        goals.add(new HealthGoal(defaultGoalPoints));
        goals.add(new DrivingGoal(defaultGoalPoints));
        goals.add(new SpendingGoal(defaultGoalPoints));
        return goals;
    }

    /**
     * Creates weekly goals for the za.ac.nwu.ac.domain.dto.member with the points they need to collect to accomplish the za.ac.nwu.ac.domain.dto.goal
     * the start date of the za.ac.nwu.ac.domain.dto.goal will be set to the current date
     *
     * @param pointsToCollect the points needed to accomplish all the goals
     */

    public void createWeeklyGoals(int pointsToCollect) {
        createHealthGoal(pointsToCollect);
        createDrivingGoal(pointsToCollect);
        createSpendingGoal(pointsToCollect);
    }

    /**
     * Creates health goal based on the points that should be collected
     *
     * @param pointsToCollect number of points necessary to complete goal
     */

    public void createHealthGoal(int pointsToCollect) {
        setHealthGoal(new HealthGoal(pointsToCollect));
    }

    /**
     * Creates driving goal based on the points that should be collected
     *
     * @param pointsToCollect number of points necessary to complete goal
     */

    public void createDrivingGoal(int pointsToCollect) {
        setDrivingGoal(new DrivingGoal(pointsToCollect));
    }

    /**
     * Creates driving goal based on the points that should be collected
     *
     * @param pointsToCollect number of points necessary to complete goal
     */

    public void createSpendingGoal(int pointsToCollect) {
        setSpendingGoal(new SpendingGoal(pointsToCollect));
    }

    /**
     * Resets goals to their initial state
     */

    public void resetGoals(){
        Goal[] goals = {getHealthGoal(),getSpendingGoal(),getDrivingGoal()};
        for (Goal goal: goals) {
            goal.setGoalAccomplished(false);
            goal.setPointsEarned(0);
            goal.setStartDate(LocalDate.now());
        }
    }

    /**
     * Translates a list of goals to individual types of goals and sets it accordingly
     *
     * @param goals List of goals
     */
    private void setGoalsFromList(List<Goal> goals) {
        for (Goal goal : goals) {
            if (goal instanceof HealthGoal)
                setHealthGoal((HealthGoal) goal);
            if (goal instanceof DrivingGoal)
                setDrivingGoal((DrivingGoal) goal);
            if (goal instanceof SpendingGoal)
                setSpendingGoal((SpendingGoal) goal);
        }
    }


    /**
     * To string method
     *
     * @return the string representation of the za.ac.nwu.ac.domain.dto.member details
     */
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", email='" + email + '\'' +
                ", miles=" + miles +
                ", gameBoard=" + gameBoard +
                ", healthGoal=" + healthGoal +
                ", drivingGoal=" + drivingGoal +
                ", spendingGoal=" + spendingGoal +
                ", rewards=" + rewards +
                '}';
    }
}
