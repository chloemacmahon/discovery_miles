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

public class Container {

    public static void main(String[] args) {
        MedicalInformation medicalInformation = new MedicalInformation(21, 56, 1.74);
        Member member = new Member("Jane", "Doe", "7894561231472", medicalInformation);
        member.chooseGameTile(3, 3);
        member.chooseGameTile(3, 1);
        member.chooseGameTile(6, 6);
        member.getGameBoard().showGameBoard();
        Reward r1 = new VoucherReward("Gift card at Woolworths food for R50", 150, "Woolworths", 50);
        member.chooseReward(r1);
        System.out.println("member.getRewards().get(0).getMileCost() = " + member.getRewards().get(0).getItemDescription());
        GameBoard gameBoard = new GameBoard(5, 5);
        System.out.println("gameBoard.revealTile(2,3) = " + gameBoard.revealTile(2, 3));
        System.out.println("gameBoard.revealTile(3,4) = " + gameBoard.revealTile(3, 4));
        gameBoard.showGameBoard();
        List<Reward> possibleRewards = new ArrayList<>();
        possibleRewards.add(r1);
        possibleRewards.add(new SubscriptionReward("Spotify 2 month subscription ", 75, "Spotify", 2));
        possibleRewards.add(new VoucherReward("Gift card at Checkers for R250", 600, "Woolworths", 250));
        Member member1 = new Member("James", "Mare", "121343543", medicalInformation);
        MemberBehaviour mb = new MemberBehaviour(member1);
        mb.addPossibleRewards(possibleRewards);
        mb.createWeeklyGoals(150);
        mb.addPointsToDrivingGoal(160);
        //mb.addPointsToHealthGoal(260);
        mb.getMember().getGameBoard().showGameBoard();
        mb.purchaseReward();
        Database db = new Database();
        //Member table created
        db.createMemberTable();
        db.createGoalTable();
        MemberBehaviour mb1 = new MemberBehaviour(member);
        mb1.createWeeklyGoals(300);
        Session session = new Session(db.insertMember(member),member);
        session.insertGoalsIntoDatabase();
        System.out.println("session.getMemberID() = " + session.getMemberID());
        db.closeConnection();
    }
}
