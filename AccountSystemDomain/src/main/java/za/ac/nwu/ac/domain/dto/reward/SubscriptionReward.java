package za.ac.nwu.ac.domain.dto.reward;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;

import javax.persistence.*;

/**
 * Represents a subscription reward that the za.ac.nwu.ac.domain.dto.member can buy with miles and get an amount of months subscription to
 */

@Data
@Entity //(name = "subscription_reward")
@Component
public class SubscriptionReward extends Reward {

    @Id
    @Column(name= "reward_id")
    private Long rewardID;

    @Column(name = "months_subscription")
    private int monthsSubscription;


    public SubscriptionReward(){};

    public SubscriptionReward(String itemDescription, int mileCost, RewardPartner rewardPartner, int monthsSubscription) {
        super(itemDescription, mileCost, rewardPartner);
        this.monthsSubscription = monthsSubscription;
    }
}
