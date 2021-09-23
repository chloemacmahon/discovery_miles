package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.reward.SubscriptionReward;

public interface SubscriptionRewardRepository extends JpaRepository<SubscriptionReward, Long> {
}
