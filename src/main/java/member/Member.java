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
    private String password;
    private String idNumber;
    //private MedicalInformation medicalInformation;
    private int miles;
    private GameBoard gameBoard;
    private List<Goal> goals;
    private List<Reward> rewards;

    public Member(String name, String surname, String password, String idNumber){ //MedicalInformation medicalInformation) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.idNumber = idNumber;
        //this.medicalInformation = medicalInformation;
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
        //this.medicalInformation = medicalInformation;
        this.miles = miles;
        this.gameBoard = new GameBoard();
    }

    public Member(String name, String surname, String password, String idNumber, int miles, List<Goal> goals, List<Reward> rewards) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.idNumber = idNumber;
        //this.medicalInformation = medicalInformation;
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
        //this.medicalInformation = medicalInformation;
        this.miles = miles;
        this.gameBoard = gameBoard;
        this.goals = goals;
        this.rewards = rewards;
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

    private Boolean isValidID(String idNumber) {
        if (idNumber.length() != 13)
            return false;
        for (char digit : idNumber.toCharArray()) {
            if (!Character.isDigit(digit))
                return false;
        }
        return luhnVerification(idNumber);
    }

    private boolean luhnVerification(String idNumber) {
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
