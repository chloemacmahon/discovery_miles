package reward.reward_partner;

import Database.Database;
import helper_classes.ReceiveInputs;
import reward.Reward;
import reward.SubscriptionReward;
import reward.VoucherReward;

public class RewardPartner {
    private String companyName;
    private String adminPassword;
    private Database db;

    public RewardPartner(String companyName, String adminPassword) {
        this.companyName = companyName;
        this.adminPassword = adminPassword;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    private Reward createReward() {
        if (ReceiveInputs.getIntInput("Enter 1 for subscription reward") == 1) {
            SubscriptionReward subscriptionReward = new SubscriptionReward(-1, ReceiveInputs.getStringInput("Enter the item description"),
                    ReceiveInputs.getIntInput("Enter the mile cost"), ReceiveInputs.getStringInput("Enter the reward partner"), ReceiveInputs.getIntInput("How many months subcription is this reward for"));
            db.insertReward(subscriptionReward);
            return subscriptionReward;
        } else {
            VoucherReward voucherReward = new VoucherReward(-1, ReceiveInputs.getStringInput("Enter the item description"),
                    ReceiveInputs.getIntInput("Enter the mile cost"), ReceiveInputs.getStringInput("Enter the reward partner"), ReceiveInputs.getIntInput("What is the monetary value of this reward?"));
            db.insertReward(voucherReward);
            return voucherReward;
        }
    }
}
