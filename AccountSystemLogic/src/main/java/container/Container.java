package container;

import Database.Database;
import member.Member;
import reward.Reward;
import reward.SubscriptionReward;
import reward.VoucherReward;
import session.Session;

import java.util.Scanner;

public class Container {

    public static void main(String[] args) {
        Session session = new Session();
        session.createGoals();
        addActivityToGoal(session);
        session.getMember().getGameBoard().showGameBoard();
        for (Reward reward : session.getMember().getRewards()) {
            System.out.println("reward.getItemDescription() = " + reward.getItemDescription());
        }

        //If goal already accomplished add miles added it allows another play on gameboard, needs fixing
        session.purchaseReward();
        session.updateDatabase();
    }

    private static void addActivityToGoal(Session session) {
        switch (getIntInput("Please 1 one to add activity to health goal, 2 for spending goal, 3 for driving goal and enter any other number to cancel")) {
            case 1:
                session.getMember().addPointsToHealthGoal(getIntInput("How many points did this activity earn you?"));
                break;
            case 2:
                session.getMember().addPointsToSpendingGoal(getIntInput("How many points did this activity earn you?"));
                break;
            case 3:
                session.getMember().addPointsToDrivingGoal(getIntInput("How many points did this activity earn you?"));
                break;
            default:
                System.out.println("No valid choice made");
                break;
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
