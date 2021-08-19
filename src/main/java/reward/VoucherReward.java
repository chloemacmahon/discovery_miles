package reward;

public class VoucherReward extends Reward{
    private double monetaryValue;

    public VoucherReward(String itemDescription, int mileCost, String rewardPartner, double monetaryValue) {
        super(itemDescription, mileCost, rewardPartner);
        this.monetaryValue = monetaryValue;
    }

    private double getMonetaryValue() {
        return monetaryValue;
    }

    private void setMonetaryValue(double monetaryValue) {
        this.monetaryValue = monetaryValue;
    }
}
