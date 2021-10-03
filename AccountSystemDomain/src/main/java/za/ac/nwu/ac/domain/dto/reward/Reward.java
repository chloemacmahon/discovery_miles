package za.ac.nwu.ac.domain.dto.reward;


import lombok.Data;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Abstract class representing a reward that a user can buy in exchange for miles, child classes are <code>SubscriptionReward</code> and <code>VoucherReward</code>
 */
@Data
@Entity
@Component
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reward implements Comparable<Reward> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reward_id")
    private Long rewardID;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "mile_cost")
    private int mileCost;

    @OneToOne
    @JoinColumn(name = "reward_partner_id")
    private RewardPartner rewardPartner;


    public Reward() {
    }

    public Reward(RewardPartner rewardPartner) {
        this.rewardPartner = rewardPartner;
    }

    protected Reward(String itemDescription, int mileCost, RewardPartner rewardPartner) {
        this.itemDescription = itemDescription;
        this.mileCost = mileCost;
        this.rewardPartner = rewardPartner;
    }

    /**
     * Sorts according to mile cost to simplify display
     * Parameters and return follow compareTo convention
     */

    public int compareTo(Reward otherReward) {
        return Integer.compare(this.getMileCost(), otherReward.getMileCost());
    }

    @Override
    public String toString() {
        return "Reward{" +
                "rewardID=" + rewardID +
                ", itemDescription='" + itemDescription + '\'' +
                ", mileCost=" + mileCost +
                ", rewardPartner='" + rewardPartner + '\'' +
                '}';
    }
}
//item description, millage value, za.ac.nwu.ac.domain.dto.reward partner