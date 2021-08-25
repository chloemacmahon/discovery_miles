package session;

import Database.Database;
import member.Member;
import reward.Reward;
import reward.SubscriptionReward;
import reward.VoucherReward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Session {
    private int memberID;
    private Member member;
    private Database db = new Database();
    private List<Reward> possibleRewards = db.listAllRewardsFromDatabase();

    public Session() {
        logInOrCreateAccount();
    }

    public Session(int memberID, Member member) {
        this.memberID = memberID;
        this.member = member;
        setPossibleRewards(db.listAllRewardsFromDatabase());
    }

    public void logInOrCreateAccount() {
        if (getIntInput("Enter 1 to log in and 2 to create an account") == 1)
            logIn();
        else {
            createMember();
        }
    }

    public void logIn() {
        String idNumber = getStringInput("What is your ID number?");
        String password = getStringInput("What is your password?");
        if (db.validateMemberAccount(idNumber, password)) {
            setMember(db.createMemberFromDatabase(idNumber));
        } else {
            createMember();
        }
        setMemberID(db.findMemberIDFromDatabase(getMember()));
    }

    public void createMember() {
        String name = getStringInput("What is your name?");
        String surname = getStringInput("What is your surname?");
        String password = getStringInput("What is your password?");
        String idNumber = getStringInput("What is your ID number?");
        /*while (!Member.isValidID(idNumber))
            idNumber = getStringInput("Please enter a valid ID number");*/
        if (db.validateMemberAccount(idNumber, password)) {
            setMember(db.createMemberFromDatabase(idNumber));
            setMemberID(db.findMemberIDFromDatabase(getMember()));
        } else if (db.memberIsInDatabase(idNumber)) {
            while (db.validateMemberAccount(idNumber, getStringInput("What is your password?"))) ;
            setMember(db.createMemberFromDatabase(idNumber));
            setMemberID(db.findMemberIDFromDatabase(getMember()));
        } else {
            Member member = new Member(name, surname, password, idNumber);
            setMemberID(db.addMemberToDatabase(member));
            setMember(member);
        }
    }

    public void updateDatabase() {
        Database db = new Database();
        db.addMemberToDatabase(getMember());
    }

    public void purchaseReward() {
        Collections.sort(possibleRewards);
        if (possibleRewards.get(0).getMileCost() < getMember().getMiles()) {
            System.out.println("Please choose a reward from the following list. Choose by entering the number of the reward");
            displayAvailableRewards(getMember().getMiles());
            int rewardPurchased = getIntInput("What reward do you want from the reward list, enter 0 or a negative value to cancel") - 1;
            if (rewardPurchased >= 0)
                getMember().chooseReward(possibleRewards.get(rewardPurchased));
        } else {
            System.out.println("You do not have enough miles for any of the available rewards");
        }
    }

    public void displayAvailableRewards(int milesAvailable) {
        Collections.sort(possibleRewards);
        for (int i = 0; i < possibleRewards.size(); i++) {
            if (possibleRewards.get(i).getMileCost() <= milesAvailable)
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

    public void createGoals() {
        member.createWeeklyGoals(getIntInput("Enter the amount of points needed to accomplish this week's goals"));
    }

    //To add a possible reward
    private void addPossibleReward(Reward reward) {
        getPossibleRewards().add(reward);
    }

    private Reward createReward() {
        if (getIntInput("Enter 1 for subscription reward") == 1) {
            SubscriptionReward subscriptionReward = new SubscriptionReward(-1, getStringInput("Enter the item description"),
                    getIntInput("Enter the mile cost"), getStringInput("Enter the reward partner"), getIntInput("How many months subcription is this reward for"));
            db.insertReward(subscriptionReward);
            return subscriptionReward;
        } else {
            VoucherReward voucherReward = new VoucherReward(-1, getStringInput("Enter the item description"),
                    getIntInput("Enter the mile cost"), getStringInput("Enter the reward partner"), getIntInput("What is the monetary value of this reward?"));
            db.insertReward(voucherReward);
            return voucherReward;
        }
    }

    private int getIntInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private static String getStringInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Reward> getPossibleRewards() {
        return possibleRewards;
    }

    public void setPossibleRewards(List<Reward> possibleRewards) {
        this.possibleRewards = db.listAllRewardsFromDatabase();
    }

    @Override
    public String toString() {
        return "Session{" +
                "memberID=" + memberID +
                ", member=" + member +
                '}';
    }
}
