package za.ac.nwu.ac.domain.dto.reward;

import lombok.Data;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Represents a voucher reward that the za.ac.nwu.ac.domain.dto.member can buy with miles and get an amount to spend at the reward partner
 */

@Data
@Entity
//@Inheritance
//@Table (name = "voucher_reward")
@Component
public class VoucherReward extends Reward {

    @Id
    @Column (name= "reward_id")
    private Long rewardID;

    @NotEmpty
    @Column (name = "monetary_value")
    private double monetaryValue;

    public VoucherReward() {
    }

    public VoucherReward(RewardPartner rewardPartner) {
        super(rewardPartner);
    }

    public VoucherReward(@NotEmpty String itemDescription, @NotEmpty int mileCost, @NotEmpty double monetaryValue) {
        super(itemDescription, mileCost);
        this.monetaryValue = monetaryValue;
    }

    public VoucherReward(String itemDescription, int mileCost, RewardPartner rewardPartner, double monetaryValue) {
        super(itemDescription, mileCost, rewardPartner);
        this.monetaryValue = monetaryValue;
    }
}
