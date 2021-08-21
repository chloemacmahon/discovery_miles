package reward;

public class SubscriptionReward extends Reward {
    private int monthsSubscription;

    public SubscriptionReward(String itemDescription, int mileCost, String rewardPartner, int monthsSubscription) {
        super(itemDescription, mileCost, rewardPartner);
        this.monthsSubscription = monthsSubscription;
    }

    private int getMonthsSubscription() {
        return monthsSubscription;
    }

    private void setMonthsSubscription(int monthsSubscription) {
        this.monthsSubscription = monthsSubscription;
    }
}
