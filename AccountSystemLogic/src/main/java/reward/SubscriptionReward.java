package reward;

public class SubscriptionReward extends Reward {
    private int monthsSubscription;

    public SubscriptionReward(int rewardID, String itemDescription, int mileCost, String rewardPartner, int monthsSubscription) {
        super(rewardID, itemDescription, mileCost, rewardPartner);
        this.monthsSubscription = monthsSubscription;
    }

    public int getMonthsSubscription() {
        return monthsSubscription;
    }

    private void setMonthsSubscription(int monthsSubscription) {
        this.monthsSubscription = monthsSubscription;
    }
}
