//package container;
//
//import za.ac.nwu.ac.domain.dto.reward.Reward;
//import session.Session;
//
//import java.util.Scanner;
//
//public class Container {
//
//    public static void main(String[] args) {
//        Session session = new Session();
//        session.createGoals();
//        addActivityToGoal(session);
//        session.getMember().getGameBoard().showGameBoard();
//        for (Reward reward : session.getMember().getRewards()) {
//            System.out.println("za.ac.nwu.ac.domain.dto.reward.getItemDescription() = " + reward.getItemDescription());
//        }
//
//        //If za.ac.nwu.ac.domain.dto.goal already accomplished add miles added it allows another play on za.ac.nwu.ac.domain.dto.gameboard, needs fixing
//        //session.purchaseReward();
//        session.updateDatabase();
//    }
//
//    private static void addActivityToGoal(Session session) {
//        switch (getIntInput("Please 1 one to add activity to health za.ac.nwu.ac.domain.dto.goal, 2 for spending za.ac.nwu.ac.domain.dto.goal, 3 for driving za.ac.nwu.ac.domain.dto.goal and enter any other number to cancel")) {
//            case 1:
//                session.getMember().addPointsToHealthGoal(getIntInput("How many points did this activity earn you?"));
//                break;
//            case 2:
//                session.getMember().addPointsToSpendingGoal(getIntInput("How many points did this activity earn you?"));
//                break;
//            case 3:
//                session.getMember().addPointsToDrivingGoal(getIntInput("How many points did this activity earn you?"));
//                break;
//            default:
//                System.out.println("No valid choice made");
//                break;
//        }
//    }
//
//    private static String getStringInput(String message) {
//        System.out.println(message);
//        Scanner input = new Scanner(System.in);
//        return input.nextLine();
//    }
//
//    private static int getIntInput(String message) {
//        System.out.println(message);
//        Scanner input = new Scanner(System.in);
//        return input.nextInt();
//    }
//
//
//}
