package container;

import Database.Database;
import gameboard.GameBoard;
import member.Member;
import member.medical_information.MedicalInformation;
import reward.Reward;
import reward.SubscriptionReward;
import reward.VoucherReward;
import session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Container {

    public static void main(String[] args) {
        Database db = new Database();
        Member user1 = new Member("Liam","Mac Mahon","Thor","2510237503123");
        MemberBehaviour mb = new MemberBehaviour(user1);
        mb.addPossibleRewards(db.listAllRewardsFromDatabase());
        mb.createWeeklyGoals(650);
        mb.addPointsToDrivingGoal(160);
        mb.addPointsToHealthGoal(260);
        mb.addPointsToHealthGoal(400);
        mb.getMember().getGameBoard().showGameBoard();
        mb.purchaseReward();
        Session session = new Session(db.addMemberToDatabase(user1),user1);
        System.out.println("session.toString() = " + session.toString());
        Member member2 = db.createMemberFromDatabase(user1.getIdNumber());
        System.out.println("member2.toString() = " + member2.toString());
        System.out.println("member2.getRewards().get(0) = " + member2.getRewards().get(0));
        System.out.println("member2.getGoals().get(0) = " + member2.getGoals().get(0));
        db.closeConnection();
    }

    private static Reward createReward(){
        if (getIntInput("Enter 1 for subscription reward")==1){
            return new SubscriptionReward(-1,getStringInput("Enter the item description"),
                    getIntInput("Enter the mile cost"),getStringInput("Enter the reward partner"),getIntInput("How many months subcription is this reward for"));

        } else {
            return new VoucherReward(-1,getStringInput("Enter the item description"),
                    getIntInput("Enter the mile cost"),getStringInput("Enter the reward partner"),getIntInput("What is the monetary value of this reward?"));
        }
    }

    private static String getStringInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static int getIntInput(String message) {
        System.out.println(message);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }


}
