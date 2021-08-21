package container;

import Database.Database;
import goal.DrivingGoal;
import goal.Goal;
import goal.HealthGoal;
import goal.SpendingGoal;
import member.Member;
import reward.Reward;
import session.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MemberBehaviour {
    private Member member;
    private static List<Reward> possibleRewards = new ArrayList<>();

    public MemberBehaviour(Member member) {
        this.member = member;
    }

    /*public Session logIn(){
        Database db = new Database();

    }*/
    /*
    public Session createMemberSession() {

    }*/

    public void purchaseReward() {
        Collections.sort(possibleRewards);
        if (possibleRewards.get(0).getMileCost() < member.getMiles()) {
            System.out.println("Please choose a reward from the following list. Choose by entering the number of the reward");
            displayAvailableRewards();
            int rewardPurchased = getIntInput("What reward do you want from the reward list, enter 0 or a negative value to cancel") - 1;
            if (rewardPurchased >= 0)
                member.chooseReward(possibleRewards.get(rewardPurchased));
        } else {
            System.out.println("You do not have enough miles for any of the available rewards");
        }
    }

    public void displayAvailableRewards() {
        Collections.sort(possibleRewards);
        for (int i = 0; i < possibleRewards.size(); i++) {
            if (possibleRewards.get(i).getMileCost() <= member.getMiles())
                System.out.println(i + 1 + " " + getPossibleRewards().get(i).getItemDescription());
        }
    }

    private void displayAllPossibleRewards() {
        for (int i = 0; i < getPossibleRewards().size(); i++) {
            System.out.println(i + 1 + " " + getPossibleRewards().get(i).getItemDescription());
        }
    }

    //To add all possible rewards
    public void addPossibleRewards(List<Reward> possibleRewards) {
        for (Reward possibleReward : possibleRewards) {
            addPossibleReward(possibleReward);
        }
    }

    //To add a possible reward
    private void addPossibleReward(Reward reward) {
        getPossibleRewards().add(reward);
    }

    public void playOnGameboard() {
        while (!getMember().chooseGameTile(getIntInput("What is the row number you want to reveal"), getIntInput("What is the column number you want to reveal")))
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
        for (Goal goal : member.getGoals()) {
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
        getMember().setGoals(goals);
    }

    private int getIntInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public static List<Reward> getPossibleRewards() {
        return possibleRewards;
    }

    public static void setPossibleRewards(List<Reward> possibleRewards) {
        MemberBehaviour.possibleRewards = possibleRewards;
    }
}
