package za.ac.nwu.ac.domain.dto.member;

import lombok.Data;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.gameboard.GameBoard;
import za.ac.nwu.ac.domain.dto.goal.DrivingGoal;
import za.ac.nwu.ac.domain.dto.goal.Goal;
import za.ac.nwu.ac.domain.dto.goal.HealthGoal;
import za.ac.nwu.ac.domain.dto.goal.SpendingGoal;
import za.ac.nwu.ac.domain.dto.helper_classes.exception.InsufficientMilesException;
import za.ac.nwu.ac.domain.dto.helper_classes.exception.InvalidGoalPointsException;
import za.ac.nwu.ac.domain.dto.reward.Reward;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static za.ac.nwu.ac.domain.dto.helper_classes.ReceiveInputs.getIntInput;


@Data
@Entity
@Component
public class Member {
    /**
     * This class represents a discovery vitality za.ac.nwu.ac.domain.dto.member
     */

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long memberId;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String password;

    @Column(name = "id_number")
    private String idNumber;

    @Column
    private int miles;

    private GameBoard gameBoard;
    private List<Goal> goals;
    private List<Reward> rewards;

    public Member(){};

    /**
     * Only personal details are received as parameters
     * @param name  Member name
     * @param surname  Member surname
     * @param password  Member account password
     * @param idNumber  Members's ID number
     */

    public Member(String name, String surname, String password, String idNumber) {
        this(name, surname, password, idNumber, 0);
    }

    /**
     * Personal details and miles are received as parameters
     * @param name  Member name
     * @param surname  Member surname
     * @param password  Member account password
     * @param idNumber  Members's ID number
     * @param miles  The za.ac.nwu.ac.domain.dto.member's available miles
     */

    public Member(String name, String surname, String password, String idNumber, int miles) {
        this(name, surname, password, idNumber, 0, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Personal details, miles, rewards and goals are received as parameters
     * @param name Member name
     * @param surname Member surname
     * @param password Member account password
     * @param idNumber Members's ID number
     * @param miles The za.ac.nwu.ac.domain.dto.member's available miles
     * @param goals The za.ac.nwu.ac.domain.dto.member's weekly goals
     * @param rewards The rewards that the za.ac.nwu.ac.domain.dto.member has accumulated thus far
     */
    public Member(String name, String surname, String password, String idNumber, int miles, List<Goal> goals, List<Reward> rewards) {
        this(name, surname, password, idNumber, 0,  new GameBoard(), goals, rewards);
    }

    /**
     * Receives all variables
     * @param name Member name
     * @param surname Member surname
     * @param password Member account password
     * @param idNumber Members's ID number
     * @param miles The za.ac.nwu.ac.domain.dto.member's available miles
     * @param gameBoard The game board that has been generated for the za.ac.nwu.ac.domain.dto.member
     * @param goals The za.ac.nwu.ac.domain.dto.member's weekly goals
     * @param rewards The rewards that the za.ac.nwu.ac.domain.dto.member has accumulated thus far
     */

    public Member(String name, String surname, String password, String idNumber, int miles, GameBoard gameBoard, List<Goal> goals, List<Reward> rewards) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.idNumber = idNumber;
        this.miles = miles;
        this.gameBoard = gameBoard;
        this.goals = goals;
        this.rewards = rewards;
    }

    /**
     * This allows the user to choose a tile on the game board to reveal and receive the miles associated to it
     * this method is triggered when a user completes a za.ac.nwu.ac.domain.dto.goal can only be used within the <code>Member</code> class
     */
    // Add exception if gametile can't be chosen
    private void playOnGameboard() {
        while (!this.chooseGameTile(getIntInput("What is the row number you want to reveal"), getIntInput("What is the column number you want to reveal")))
            System.out.println("Please choose another tile as this one is not available to change");
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's health za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the za.ac.nwu.ac.domain.dto.member can reveal a tile on the game board, calls <code>playOnGameboard</code>
     * @param points the amount of points that the user has earn by doing an activity relating to their health, spending or driving za.ac.nwu.ac.domain.dto.goal
     */

    public void addPointsToHealthGoal(int points) {
        if (addPointsToGoal(points, 0))
            playOnGameboard();
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's driving za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the za.ac.nwu.ac.domain.dto.member can reveal a tile on the game board, calls <code>playOnGameboard</code>
     * @param points the amount of points that the user has earn by doing an activity relating to their health, spending or driving za.ac.nwu.ac.domain.dto.goal
     */

    public void addPointsToDrivingGoal(int points) {
        if (addPointsToGoal(points, 1))
            playOnGameboard();
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's spending za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the za.ac.nwu.ac.domain.dto.member can reveal a tile on the game board, calls <code>playOnGameboard</code>
     * @param points the amount of points that the user has earn by doing an activity relating to their health, spending or driving za.ac.nwu.ac.domain.dto.goal
     */

    public void addPointsToSpendingGoal(int points) {
        if (addPointsToGoal(points, 2))
            playOnGameboard();
    }

    /**
     * This method allows points to be added to a specific za.ac.nwu.ac.domain.dto.goal, can only be accessed within za.ac.nwu.ac.domain.dto.member class and
     *  is call by <code>addPointsToHealthGoal</code>, <code>addPointsToDrivingGoal</code> and <code>addPointsToSpendingGoal</code>
     * @param points represents the amount of points earned by completing a specific activity
     * @param goalType 0 represents health za.ac.nwu.ac.domain.dto.goal, 1 represents driving za.ac.nwu.ac.domain.dto.goal, 2 represents spending za.ac.nwu.ac.domain.dto.goal
     * @return true if the points were enough to accomplish the weekly za.ac.nwu.ac.domain.dto.goal, false if not
     * Catches the exception thrown by <code>addPoints</code>
     */

    private boolean addPointsToGoal(int points, int goalType) {
        for (Goal goal : this.getGoals()) {
            if (goalType == 0 && goal instanceof HealthGoal) {
                try {
                    goal.addPoints(points);
                    return goal.isGoalAccomplished();
                } catch (InvalidGoalPointsException invalidGoalPointsException) {
                    return false;
                }
            }
            if (goalType == 1 && goal instanceof DrivingGoal) {
                try {
                    goal.addPoints(points);
                    return goal.isGoalAccomplished();
                } catch (InvalidGoalPointsException invalidGoalPointsException) {
                    return false;
                }
            }
            if (goalType == 2 && goal instanceof SpendingGoal) {
                try {
                    goal.addPoints(points);
                    return goal.isGoalAccomplished();
                } catch (InvalidGoalPointsException invalidGoalPointsException) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Creates weekly goals for the za.ac.nwu.ac.domain.dto.member with the points they need to collect to accomplish the za.ac.nwu.ac.domain.dto.goal
     *  the start date of the za.ac.nwu.ac.domain.dto.goal will be set to the current date
     * @param pointsToCollect the points needed to accomplish all the goals
     */

    public void createWeeklyGoals(int pointsToCollect) {
        List<Goal> goals = new ArrayList<>();
        goals.add(new HealthGoal(pointsToCollect,getMemberId()));
        goals.add(new DrivingGoal(pointsToCollect,getMemberId()));
        goals.add(new SpendingGoal(pointsToCollect,getMemberId()));
        this.setGoals(goals);
    }

    /**
     * This method allows a za.ac.nwu.ac.domain.dto.member to add a za.ac.nwu.ac.domain.dto.reward to their rewards and subtracts the miles from their available miles
     * <code>subtractMiles</code>, <code>addReward</code> are called
     * If there is insufficient miles available <code>chooseReward</code> catches the exception
     * @param reward the za.ac.nwu.ac.domain.dto.reward that the za.ac.nwu.ac.domain.dto.member wants to choose as their za.ac.nwu.ac.domain.dto.reward
     */

    public void chooseReward(Reward reward) {
        try {
            subtractMiles(reward.getMileCost());
            addReward(reward);
            System.out.println("You have purchased " + reward.getItemDescription() + " as your za.ac.nwu.ac.domain.dto.reward your miles are " + getMiles());
        } catch (Exception e) {
            System.out.println("Not enough points available for this award");
        }
    }

    /**
     * Adds za.ac.nwu.ac.domain.dto.reward to the user za.ac.nwu.ac.domain.dto.reward list
     * Is called by <code>chooseReward</code>
     * @param reward the za.ac.nwu.ac.domain.dto.reward that the za.ac.nwu.ac.domain.dto.member has chosen to add to their za.ac.nwu.ac.domain.dto.reward
     */

    private void addReward(Reward reward) {
        getRewards().add(reward);
    }

    /**
     * Allows user to choose a tile to reveal then adds the miles to the za.ac.nwu.ac.domain.dto.member
     * Calls the <code>revealTile</code> method of the <code>GameBoard</code> class
     * @param row the row number of the tile that the user wants to reveal
     * @param column the column number of the tile that the user wants to reveal
     * @return true if the game tile was successfully chosen
     */

    public boolean chooseGameTile(int row, int column) {
        int miles = getGameBoard().revealTile(row, column);
        if (miles < 0) {
            System.out.println("This tile is not available to reveal");
            return false;
        } else {
            addMiles(miles);
            System.out.println("You have earned " + miles + " miles by revealing this tile, your total miles is now " + getMiles());
            return true;
        }
    }

    /**
     * Adds miles to the za.ac.nwu.ac.domain.dto.member's available miles
     * @param miles miles to be added to the available miles
     */

    public void addMiles(int miles) {
        setMiles(getMiles() + miles);
    }

    /**
     * Subtract miles from the za.ac.nwu.ac.domain.dto.member's available miles
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
     * Validates the za.ac.nwu.ac.domain.dto.member's ID number
     * @param idNumber the za.ac.nwu.ac.domain.dto.member's ID number
     * @return true if the za.ac.nwu.ac.domain.dto.member's ID number is valid
     */

    public static Boolean isValidID(String idNumber) {
        if (idNumber.length() != 13)
            return false;
        for (char digit : idNumber.toCharArray()) {
            if (!Character.isDigit(digit))
                return false;
        }
        char[] idNumberArray = idNumber.toCharArray();
        if ((Character.getNumericValue(idNumberArray[2])+Character.getNumericValue(idNumberArray[3])*10)>12)
            return false;
        if ((Character.getNumericValue(idNumberArray[4])+Character.getNumericValue(idNumberArray[5])*10)>31)
            return false;
        return true;//luhnVerification(idNumber);
    }

    /**
     * Verifies the za.ac.nwu.ac.domain.dto.member's ID number with the luhn algorithm
     * @param idNumber the za.ac.nwu.ac.domain.dto.member's ID number
     * @return true if the za.ac.nwu.ac.domain.dto.member's ID number is correct according to the luhn algorithm
     */

    private static boolean luhnVerification(String idNumber) {
        int addedValue = 0;
        for (int i = 0; i < idNumber.length()-1; i++) {
            if (i % 2 == 0) {
                addedValue += idNumber.charAt(i);
            } else {
                int doubledValue = idNumber.charAt(i) * 2;
                addedValue += doubledValue % 10 + Math.floorMod(doubledValue, 10);
            }
        }
        System.out.println("" +addedValue / 10);
        return addedValue / 10 == 0;
    }

    /**
     * To string method
     * @return the string representation of the za.ac.nwu.ac.domain.dto.member details
     */

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", miles=" + miles +
                ", gameBoard=" + gameBoard +
                ", goals=" + goals +
                ", rewards=" + rewards +
                '}';
    }
}
