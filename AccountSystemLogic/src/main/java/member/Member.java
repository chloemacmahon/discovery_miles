package member;

import gameboard.GameBoard;
import goal.DrivingGoal;
import goal.Goal;
import goal.HealthGoal;
import goal.SpendingGoal;
import helper_classes.exception.InsufficientMilesException;
import helper_classes.exception.InvalidGoalPointsException;
import reward.Reward;

import java.util.ArrayList;
import java.util.List;

import static helper_classes.ReceiveInputs.getIntInput;

public class Member {
    /**
     * This class represents a discovery vitality member
     */
    private String name;
    private String surname;
    private String password;
    private String idNumber;
    private int miles;
    private GameBoard gameBoard;
    private List<Goal> goals;
    private List<Reward> rewards;

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
     * @param miles  The member's available miles
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
     * @param miles The member's available miles
     * @param goals The member's weekly goals
     * @param rewards The rewards that the member has accumulated thus far
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
     * @param miles The member's available miles
     * @param gameBoard The gameboard that has been generated for the member
     * @param goals The member's weekly goals
     * @param rewards The rewards that the member has accumulated thus far
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
     * This allows the user to choose a tile on the gameboard to reveal and receive the miles associated to it
     * this method is triggered when a user completes a goal can only be used within the <code>Member</code> class
     */
    // Add exception if gametile can't be chosen
    private void playOnGameboard() {
        while (!this.chooseGameTile(getIntInput("What is the row number you want to reveal"), getIntInput("What is the column number you want to reveal")))
            System.out.println("Please choose another tile as this one is not available to change");
    }

    /**
     * Adds points to the member's health goal
     * If the goal is accomplished the member can reveal a tile on the gameboard, calls <code>playOnGameboard</code>
     * @param points the amount of points that the user has earn by doing an activity relating to their health, spending or driving goal
     */

    public void addPointsToHealthGoal(int points) {
        if (addPointsToGoal(points, 0))
            playOnGameboard();
    }

    /**
     * Adds points to the member's driving goal
     * If the goal is accomplished the member can reveal a tile on the gameboard, calls <code>playOnGameboard</code>
     * @param points the amount of points that the user has earn by doing an activity relating to their health, spending or driving goal
     */

    public void addPointsToDrivingGoal(int points) {
        if (addPointsToGoal(points, 1))
            playOnGameboard();
    }

    /**
     * Adds points to the member's spending goal
     * If the goal is accomplished the member can reveal a tile on the gameboard, calls <code>playOnGameboard</code>
     * @param points the amount of points that the user has earn by doing an activity relating to their health, spending or driving goal
     */

    public void addPointsToSpendingGoal(int points) {
        if (addPointsToGoal(points, 2))
            playOnGameboard();
    }

    /**
     * This method allows points to be added to a specific goal, can only be accessed within member class and
     *  is call by <code>addPointsToHealthGoal</code>, <code>addPointsToDrivingGoal</code> and <code>addPointsToSpendingGoal</code>
     * @param points represents the amount of points earned by completing a specific activity
     * @param goalType 0 represents health goal, 1 represents driving goal, 2 represents spending goal
     * @return true if the points were enough to accomplish the weekly goal, false if not
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
     * Creates weekly goals for the member with the points they need to collect to accomplish the goal
     *  the start date of the goal will be set to the current date
     * @param pointsToCollect the points needed to accomplish all the goals
     */

    public void createWeeklyGoals(int pointsToCollect) {
        List<Goal> goals = new ArrayList<>();
        goals.add(new HealthGoal(pointsToCollect, -1));
        goals.add(new DrivingGoal(pointsToCollect, -1));
        goals.add(new SpendingGoal(pointsToCollect, -1));
        this.setGoals(goals);
    }

    /**
     * This method allows a member to add a reward to their rewards and subtracts the miles from their available miles
     * <code>subtractMiles</code>, <code>addReward</code> are called
     * If there is insufficient miles available <code>chooseReward</code> catches the exception
     * @param reward the reward that the member wants to choose as their reward
     */

    public void chooseReward(Reward reward) {
        try {
            subtractMiles(reward.getMileCost());
            addReward(reward);
            System.out.println("You have purchased " + reward.getItemDescription() + " as your reward your miles are " + getMiles());
        } catch (Exception e) {
            System.out.println("Not enough points available for this award");
        }
    }

    /**
     * Adds reward to the user reward list
     * Is called by <code>chooseReward</code>
     * @param reward the reward that the member has chosen to add to their reward
     */

    private void addReward(Reward reward) {
        getRewards().add(reward);
    }

    /**
     * Allows user to choose a tile to reveal then adds the miles to the member
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
     * Adds miles to the member's available miles
     * @param miles miles to be added to the available miles
     */

    public void addMiles(int miles) {
        setMiles(getMiles() + miles);
    }

    /**
     * Subtract miles from the member's available miles
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
     * Validates the member's ID number
     * @param idNumber the member's ID number
     * @return true if the member's ID number is valid
     */

    public static Boolean isValidID(String idNumber) {
        if (idNumber.length() != 13)
            return false;
        for (char digit : idNumber.toCharArray()) {
            if (!Character.isDigit(digit))
                return false;
        }
        return luhnVerification(idNumber);
    }

    /**
     * Verifies the member's ID number with the luhn algorithm
     * @param idNumber the member's ID number
     * @return true if the member's ID number is correct according to the luhn algorithm
     */

    private static boolean luhnVerification(String idNumber) {
        int addedValue = 0;
        for (int i = 0; i < idNumber.length(); i++) {
            if (i % 2 == 0) {
                addedValue += idNumber.charAt(i);
            } else {
                int doubledValue = idNumber.charAt(i) * 2;
                addedValue += doubledValue % 10 + Math.floorMod(doubledValue, 10);
            }
        }
        return addedValue / 10 == 0;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    private void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getMiles() {
        return miles;
    }

    private void setMiles(int miles) {
        this.miles = miles;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * To string method
     * @return the string representation of the member details
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
