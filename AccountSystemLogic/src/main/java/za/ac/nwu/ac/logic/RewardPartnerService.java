package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.reward.SubscriptionReward;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;

public interface RewardPartnerService {

    RewardPartner registerRewardPartner(String companyName, String email, String adminPassword, int controlPasswordGiven);

    RewardPartner logInRewardPartner(String email, String password);

    SubscriptionReward createSubscriptionReward(String itemDescription, int mileCost, int monthsSubscription , RewardPartner rewardPartner);

    VoucherReward createVoucherReward(String itemDescription, int mileCost, double monetaryValue , RewardPartner rewardPartner);

    //VoucherReward createVoucherRewardWithDifferentCurrency(String itemDescription, int mileCost, double monetaryValue , RewardPartner rewardPartner, ExchangeRate exchangeRate);
}
