package za.ac.nwu.ac.domain.dto.reward;

import lombok.Data;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;

import javax.persistence.*;

/**
 * Represents a voucher reward that the za.ac.nwu.ac.domain.dto.member can buy with miles and get an amount to spend at the reward partner
 */

@Data
@Entity
@Table (name = "voucher_reward")
@Component
public class VoucherReward extends Reward {

    @Id
    @Column (name= "reward_id")
    private Long rewardID;

    @Column (name = "monetary_value")
    private double monetaryValue;

    @JoinColumn (name = "reward_id")
    private Reward reward;

    public VoucherReward(String itemDescription, int mileCost, RewardPartner rewardPartner, double monetaryValue) {
        super(itemDescription, mileCost, rewardPartner);
        this.monetaryValue = monetaryValue;
    }
}