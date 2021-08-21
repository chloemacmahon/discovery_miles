package reward;

public class VoucherReward extends Reward {
    private double monetaryValue;

    public VoucherReward(int rewardID, String itemDescription, int mileCost, String rewardPartner, double monetaryValue) {
        super(rewardID, itemDescription, mileCost, rewardPartner);
        this.monetaryValue = monetaryValue;
    }

    public double getMonetaryValue() {
        return monetaryValue;
    }

    private void setMonetaryValue(double monetaryValue) {
        this.monetaryValue = monetaryValue;
    }
}
