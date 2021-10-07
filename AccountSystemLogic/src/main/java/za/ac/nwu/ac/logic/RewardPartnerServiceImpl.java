package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.reward.SubscriptionReward;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;
import za.ac.nwu.ac.domain.exception.*;
import za.ac.nwu.ac.repository.RewardPartnerRepository;
import za.ac.nwu.ac.repository.RewardRepository;
import za.ac.nwu.ac.repository.SubscriptionRewardRepository;
import za.ac.nwu.ac.repository.VoucherRewardRepository;


@Component
public class RewardPartnerServiceImpl implements RewardPartnerService {

    private static final int controlPassword = 6523;

    private RewardPartnerRepository rewardPartnerRepository;

    private RewardRepository rewardRepository;

    private VoucherRewardRepository voucherRewardRepository;

    private SubscriptionRewardRepository subscriptionRewardRepository;

    @Autowired
    public RewardPartnerServiceImpl(RewardPartnerRepository rewardPartnerRepository, RewardRepository rewardRepository, VoucherRewardRepository voucherRewardRepository, SubscriptionRewardRepository subscriptionRewardRepository) {
        this.rewardPartnerRepository = rewardPartnerRepository;
        this.rewardRepository = rewardRepository;
        this.voucherRewardRepository = voucherRewardRepository;
        this.subscriptionRewardRepository = subscriptionRewardRepository;
    }

    public RewardPartner registerRewardPartner(String companyName, String email, String password, int controlPasswordGiven) {
        if (controlPassword != controlPasswordGiven) {
            throw new FailedToCreateRewardPartnerException("Password does not match control password");
        }
        if (!Validator.isValidEmail(email)) {
            throw new InvalidEmailException();
        }
        if (Validator.isValidPassword(password)) {
            RewardPartner rewardPartner = new RewardPartner(companyName, email, password);
            rewardPartnerRepository.save(rewardPartner);
            return rewardPartner;
        } else {
            throw new InvalidPasswordException();
        }
    }

    public RewardPartner logInRewardPartner(String email, String password) {
        RewardPartner rewardPartner = rewardPartnerRepository.findByEmail(email);
        if (rewardPartner == null) {
            throw new FailedToLogInException("No such user in the system");
        }
        if (rewardPartner.getAdminPassword().equals(password))
            return rewardPartner;
        else {
            throw new IncorrectPasswordException();
        }
    }

    public SubscriptionReward createSubscriptionReward(String itemDescription, int mileCost, int monthsSubscription, RewardPartner rewardPartner) {
        if (mileCost < 0 || monthsSubscription < 0) {
            throw new FailedToCreateRewardException();
        }
        SubscriptionReward subscriptionReward = new SubscriptionReward(itemDescription, mileCost, rewardPartner, monthsSubscription);
        subscriptionRewardRepository.save(subscriptionReward);
        return subscriptionReward;
    }

    public VoucherReward createVoucherReward(String itemDescription, int mileCost, double monetaryValue, RewardPartner rewardPartner) {
        if (rewardPartner == null) {
            throw new FailedToCreateRewardException();
        }
        if (mileCost < 0 || monetaryValue < 0) {
            throw new FailedToCreateRewardException();
        }
        VoucherReward voucherReward = new VoucherReward(itemDescription, mileCost, rewardPartner, monetaryValue);
        voucherRewardRepository.save(voucherReward);
        return voucherReward;
    }

    public RewardPartner findRewardPartnerById(Long id) {
        try {
            return rewardPartnerRepository.findById(id).get();
        } catch (RuntimeException e) {
            throw new FailedToCreateRewardPartnerException();
        }
    }

}

