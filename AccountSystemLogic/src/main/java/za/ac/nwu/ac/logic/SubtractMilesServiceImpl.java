package za.ac.nwu.ac.logic;

import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward.Reward;

@Component
public class SubtractMilesServiceImpl implements SubractMilesService{

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
            System.out.println("You have purchased " + reward.getItemDescription() + " as your za.ac.nwu.ac.domain.dto.reward your miles are " + member.getMiles());
        } catch (Exception e) {
            System.out.println("Not enough points available for this award");
        }
    }


}
