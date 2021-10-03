package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward.SubscriptionReward;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;
import za.ac.nwu.ac.domain.exception.*;
import za.ac.nwu.ac.repository.*;


@Component
public class RewardPartnerServiceImpl implements RewardPartnerService{

    private static final int controlPassword = 6523;

    private RewardPartnerRepository rewardPartnerRepository;

    private RewardRepository rewardRepository;

    @Autowired
    public RewardPartnerServiceImpl(RewardPartnerRepository rewardPartnerRepository, RewardRepository rewardRepository) {
        this.rewardPartnerRepository = rewardPartnerRepository;
        this.rewardRepository = rewardRepository;
    }

    public RewardPartner registerRewardPartner(String companyName, String email, String password, int controlPasswordGiven){
        if (controlPassword == controlPasswordGiven){
            if (Validator.isValidEmail(email)) {
                if (Validator.isValidPassword(password)) {
                    RewardPartner rewardPartner = new RewardPartner(companyName, email, password);
                    rewardPartnerRepository.save(rewardPartner);
                    return rewardPartner;
                } else
                    throw new InvalidPasswordException();
            } else
                throw new InvalidEmailException();
        } else
            throw new FailedToCreateRewardPartnerException("Password does not match control password");
    }

    public RewardPartner logInRewardPartner(String email, String password){
        RewardPartner rewardPartner = rewardPartnerRepository.findByEmail(email);
        if (rewardPartner == null){
            throw new FailedToLogInException("No such user in the system");
        }
        if (rewardPartner.getAdminPassword().equals(password))
            return rewardPartner;
        else {
            throw new IncorrectPasswordException();
        }
    }

    public SubscriptionReward createSubscriptionReward(String itemDescription, int mileCost, int monthsSubscription , RewardPartner rewardPartner){
        SubscriptionReward subscriptionReward = new SubscriptionReward(itemDescription,mileCost,rewardPartner,monthsSubscription);
        rewardRepository.save(subscriptionReward);
        return subscriptionReward;
    }

    public VoucherReward createVoucherReward(String itemDescription, int mileCost, double monetaryValue , RewardPartner rewardPartner) {
        VoucherReward voucherReward = new VoucherReward(itemDescription,mileCost,rewardPartner,monetaryValue);
        rewardRepository.save(voucherReward);
        return voucherReward;
    }

}

