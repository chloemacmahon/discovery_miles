package reward;

public abstract class Reward implements Comparable<Reward>{
    private String itemDescription;
    private int mileCost;
    private String rewardPartner;

    public Reward(String itemDescription, int mileCost, String rewardPartner) {
        this.itemDescription = itemDescription;
        this.mileCost = mileCost;
        this.rewardPartner = rewardPartner;
    }

    public int compareTo(Reward otherReward){
        return Integer.compare(this.getMileCost(),otherReward.getMileCost());
    }

    public String getItemDescription() {
        return itemDescription;
    }

    private void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getMileCost() {
        return mileCost;
    }

    private void setMileCost(int mileCost) {
        this.mileCost = mileCost;
    }

    public String getRewardPartner() {
        return rewardPartner;
    }

    private void setRewardPartner(String rewardPartner) {
        this.rewardPartner = rewardPartner;
    }
}
    //item description, millage value, reward partner