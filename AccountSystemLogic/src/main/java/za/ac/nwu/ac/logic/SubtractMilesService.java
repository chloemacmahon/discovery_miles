package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward.Reward;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

public interface SubtractMilesService {

    void chooseReward(Reward reward, Member member);

    List<Reward> showAllReward();

    List<Reward> showAllAffordableRewards(int milesAvailable);
}
