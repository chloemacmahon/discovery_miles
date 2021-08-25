package member;

import gameboard.GameBoard;
import goal.DrivingGoal;
import goal.Goal;
import goal.HealthGoal;
import goal.SpendingGoal;
import member.medical_information.MedicalInformation;
import reward.Reward;
import session.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Member {
    private String name;
    private String surname;
    private String password;
    private String idNumber;
    private int miles;
    private GameBoard gameBoard;
    private List<Goal> goals;
    private List<Reward> rewards;

    public Member(String name, String surname, String password, String idNumber) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.idNumber = idNumber;
        this.miles = 0;
        this.gameBoard = new GameBoard();
        this.goals = new ArrayList<>();
        this.rewards = new ArrayList<>();
    }

    public Member(String name, String surname, String password, String idNumber, int miles) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.idNumber = idNumber;
        this.miles = miles;
        this.gameBoard = new GameBoard();
    }

    public Member(String name, String surname, String password, String idNumber, int miles, List<Goal> goals, List<Reward> rewards) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.idNumber = idNumber;
        this.miles = miles;
        this.gameBoard = new GameBoard();
        this.goals = goals;
        this.rewards = rewards;
    }


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

    public void playOnGameboard() {
        while (!this.chooseGameTile(getIntInput("What is the row number you want to reveal"), getIntInput("What is the column number you want to reveal")))
            System.out.println("Please choose another tile as this one is not available to change");
    }

    //Allows a play on the gameboard if the goal is accomplished
    public void addPointsToHealthGoal(int points) {
        if (addPointsToGoal(points, 0))
            playOnGameboard();
    }

    //Allows a play on the gameboard if the goal is accomplished
    public void addPointsToDrivingGoal(int points) {
        if (addPointsToGoal(points, 1))
            playOnGameboard();
    }

    //Allows a play on the gameboard if the goal is accomplished
    public void addPointsToSpendingGoal(int points) {
        if (addPointsToGoal(points, 2))
            playOnGameboard();
    }

    //0 represents health goal, 1 represents driving goal, 2 represents spending goal, returns true if goal is accomplished
    private boolean addPointsToGoal(int points, int goalType) {
        for (Goal goal : this.getGoals()) {
            if (goalType == 0 && goal instanceof HealthGoal) {
                goal.addPoints(points);
                return goal.isGoalAccomplished();
            }
            if (goalType == 1 && goal instanceof DrivingGoal) {
                goal.addPoints(points);
                return goal.isGoalAccomplished();
            }
            if (goalType == 2 && goal instanceof SpendingGoal) {
                goal.addPoints(points);
                return goal.isGoalAccomplished();
            }
        }
        return false;
    }

    public void createWeeklyGoals(int pointsToCollect) {
        List<Goal> goals = new ArrayList<>();
        goals.add(new HealthGoal(pointsToCollect, -1));
        goals.add(new DrivingGoal(pointsToCollect, -1));
        goals.add(new SpendingGoal(pointsToCollect, -1));
        this.setGoals(goals);
    }

    private int getIntInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public void chooseReward(Reward reward) {
        try {
            subtractMiles(reward.getMileCost());
            addReward(reward);
            System.out.println("You have purchased " + reward.getItemDescription() + " as your reward your miles are " + getMiles());
        } catch (Exception e) {
            System.out.println("Not enough points available for this award");
        }
    }

    private void addReward(Reward reward) {
        getRewards().add(reward);
    }

    //Returns true if the tile can be chooses
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

    public void addMiles(int miles) {
        setMiles(getMiles() + miles);
    }

    public void subtractMiles(int miles) throws Exception {
        if (getMiles() - miles < 0)
            throw new Exception("Not enough miles available");
        else
            setMiles(getMiles() - miles);
    }

    public static Boolean isValidID(String idNumber) {
        if (idNumber.length() != 13)
            return false;
        for (char digit : idNumber.toCharArray()) {
            if (!Character.isDigit(digit))
                return false;
        }
        return luhnVerification(idNumber);
    }

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

    /*public MedicalInformation getMedicalInformation() {
        return medicalInformation;
    }

    private void setMedicalInformation(MedicalInformation medicalInformation) {
        this.medicalInformation = medicalInformation;
    }*/

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
