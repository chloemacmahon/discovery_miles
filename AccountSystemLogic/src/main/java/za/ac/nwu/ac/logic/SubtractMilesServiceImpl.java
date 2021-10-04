package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward.Reward;
import za.ac.nwu.ac.domain.exception.InsufficientMilesException;
import za.ac.nwu.ac.repository.MemberRepository;
import za.ac.nwu.ac.repository.RewardRepository;

import java.util.List;

@Component
public class SubtractMilesServiceImpl implements SubtractMilesService {

    private RewardRepository rewardRepository;
    private MemberRepository memberRepository;

    @Autowired
    public SubtractMilesServiceImpl(RewardRepository rewardRepository, MemberRepository memberRepository) {
        this.rewardRepository = rewardRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * This method allows a za.ac.nwu.ac.domain.dto.member to add a za.ac.nwu.ac.domain.dto.reward to their rewards and subtracts the miles from their available miles
     * <code>subtractMiles</code>, <code>addReward</code> are called
     * If there is insufficient miles available <code>chooseReward</code> catches the exception
     *
     * @param reward the za.ac.nwu.ac.domain.dto.reward that the za.ac.nwu.ac.domain.dto.member wants to choose as their za.ac.nwu.ac.domain.dto.reward
     */

    public void chooseReward(Reward reward, Member member) {
        try {
            member.subtractMiles(reward.getMileCost());
            member.addReward(reward);
            memberRepository.save(member);
        } catch (Exception e) {
            throw new InsufficientMilesException();
        }
    }

    public List<Reward> showAllReward() {
        return rewardRepository.findAll();
    }

    public List<Reward> showAllAffordableRewards(int milesAvailable){
        return rewardRepository.findByMileCostLessThan(milesAvailable);
    }

}
