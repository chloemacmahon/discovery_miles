package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.reward.SubscriptionReward;

@Repository
public interface SubscriptionRewardRepository extends JpaRepository<SubscriptionReward, Long> {
}
