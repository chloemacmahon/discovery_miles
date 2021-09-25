//package session;
//
////import Database.Database;
//import za.ac.nwu.ac.domain.dto.member.Member;
//import za.ac.nwu.ac.domain.dto.reward.Reward;
//
//import za.ac.nwu.ac.domain.dto.helper_classes.ReceiveInputs;
//
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.Collections;
//import java.util.List;
//
///**
// * Handles the session things
// */
//public class Session {
//    private int memberID;
//    private Member member;
//    //private Database db = new Database();
//    //private List<Reward> possibleRewards = db.listAllRewardsFromDatabase();
//
//    public Session() {
//        logInOrCreateAccount();
//    }
//
//    public Session(int memberID, Member member) {
//        this.memberID = memberID;
//        this.member = member;
//        //setPossibleRewards(db.listAllRewardsFromDatabase());
//
////    public void purchaseReward() {
////        Collections.sort(possibleRewards);
////        if (possibleRewards.get(0).getMileCost() < getMember().getMiles()) {
////            System.out.println("Please choose a za.ac.nwu.ac.domain.dto.reward from the following list. Choose by entering the number of the za.ac.nwu.ac.domain.dto.reward");
////            displayAvailableRewards(getMember().getMiles());
////            int rewardPurchased = ReceiveInputs.getIntInput("What za.ac.nwu.ac.domain.dto.reward do you want from the za.ac.nwu.ac.domain.dto.reward list, enter 0 or a negative value to cancel") - 1;
////            if (rewardPurchased >= 0)
////                getMember().chooseReward(possibleRewards.get(rewardPurchased));
////        } else {
////            System.out.println("You do not have enough miles for any of the available rewards");
////        }
////    }
////
////    public void displayAvailableRewards(int milesAvailable) {
////        Collections.sort(possibleRewards);
////        for (int i = 0; i < possibleRewards.size(); i++) {
////            if (possibleRewards.get(i).getMileCost() <= milesAvailable)
////                System.out.println(i + 1 + " " + getPossibleRewards().get(i).getItemDescription());
////        }
////    }
//
////    private void displayAllPossibleRewards() {
////        for (int i = 0; i < getPossibleRewards().size(); i++) {
////            System.out.println(i + 1 + " " + getPossibleRewards().get(i).getItemDescription());
////        }
////    }
//
//    //To add all possible rewards
//    public void addPossibleRewards(List<Reward> possibleRewards) {
//        for (Reward possibleReward : possibleRewards) {
//            addPossibleReward(possibleReward);
//        }
//    }
//
//    public void createGoals() {
//        member.createWeeklyGoals(ReceiveInputs.getIntInput("Enter the amount of points needed to accomplish this week's goals"));
//    }
//
//    //To add a possible za.ac.nwu.ac.domain.dto.reward
//    private void addPossibleReward(Reward reward) {
//        //getPossibleRewards().add(reward);
//    }
//
//    public int getMemberID() {
//        return memberID;
//    }
//
//    public void setMemberID(int memberID) {
//        this.memberID = memberID;
//    }
//
//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
//
////    public List<Reward> getPossibleRewards() {
////        return possibleRewards;
////    }
////
////    public void setPossibleRewards(List<Reward> possibleRewards) {
////        this.possibleRewards = db.listAllRewardsFromDatabase();
////    }
//
//    @Override
//    public String toString() {
//        return "Session{" +
//                "memberID=" + memberID +
//                ", za.ac.nwu.ac.domain.dto.member=" + member +
//                '}';
//    }
//}
