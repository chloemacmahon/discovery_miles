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

        MedicalInformation medicalInformation = new MedicalInformation(21, 56, 1.74);
        Member member = new Member("Jane", "Doe", "fjoejwd", "7894561231472");
        Reward r1 = new VoucherReward(1,"Gift card at Woolworths food for R50", 150, "Woolworths", 50);
        member.chooseReward(r1);
        Member member1 = new Member("James", "Mare", "Ajkdjsw", "121343543");
        MemberBehaviour mb = new MemberBehaviour(member1);
        mb.addPossibleRewards(db.listAllRewardsFromDatabase());
        mb.createWeeklyGoals(150);
        mb.addPointsToDrivingGoal(160);
        //mb.addPointsToHealthGoal(260);
        mb.getMember().getGameBoard().showGameBoard();
        mb.purchaseReward();


        MemberBehaviour mb1 = new MemberBehaviour(member);
        mb1.createWeeklyGoals(300);
        Session session = new Session(db.insertMember(member),member);
        /*//session.insertGoalsIntoDatabase();
        db.insertReward(r1);
        db.insertReward(MemberBehaviour.getPossibleRewards().get(0));
        System.out.println("session.getMemberID() = " + session.getMemberID());*/
        db.insertReward(createReward());
        List<Reward> rewards = db.listAllRewardsFromDatabase();
        for (Reward reward : rewards) {
            System.out.println("reward.getItemDescription() = " + reward.getItemDescription());
        }
        System.out.println("db.validateMemberAccount(member) = " + db.validateMemberAccount(member));
        System.out.println("db.memberIsInDatabase(member1) = " + db.memberIsInDatabase(member));
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
