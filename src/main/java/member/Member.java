package member;

import gameboard.GameBoard;
import goal.Goal;
import member.medical_information.MedicalInformation;
import reward.Reward;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String surname;
    private String idNumber;
    private MedicalInformation medicalInformation;
    private int miles;
    private GameBoard gameBoard;
    private List<Goal> goals;
    private List<Reward> rewards;

    public Member(String name, String surname, String idNumber, MedicalInformation medicalInformation) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.medicalInformation = medicalInformation;
        this.miles = 0;
        this.gameBoard = new GameBoard();
        this.goals = new ArrayList<>();
        this.rewards = new ArrayList<>();
    }

    public Member(String name, String surname, String idNumber, MedicalInformation medicalInformation, int miles, List<Goal> goals, List<Reward> rewards) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.medicalInformation = medicalInformation;
        this.miles = miles;
        this.gameBoard = new GameBoard();
        this.goals = goals;
        this.rewards = rewards;
    }

    //Remember to refactor medical info
    public Member(String name, String surname, String idNumber, MedicalInformation medicalInformation, int miles, GameBoard gameBoard, List<Goal> goals, List<Reward> rewards) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.medicalInformation = medicalInformation;
        this.miles = miles;
        this.gameBoard = gameBoard;
        this.goals = goals;
        this.rewards = rewards;
    }

    public void chooseReward(Reward reward) {
        try {
            subtractMiles(reward.getMileCost());
            addReward(reward);
            System.out.println("You have purchased "+reward.getItemDescription()+" as your reward your miles are "+ getMiles());
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
        }else {
            addMiles(miles);
            System.out.println("You have earned "+ miles + " miles by revealing this tile, your total miles is now "+getMiles());
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

    private Boolean isValidID(String idNumber) {
        //to-do
        return true;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    private String getIdNumber() {
        return idNumber;
    }

    private void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    private MedicalInformation getMedicalInformation() {
        return medicalInformation;
    }

    private void setMedicalInformation(MedicalInformation medicalInformation) {
        this.medicalInformation = medicalInformation;
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
}
