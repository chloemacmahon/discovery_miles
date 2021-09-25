package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward.Reward;

public interface SubractMilesService {

    void chooseReward(Reward reward, Member member);
}
