package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.reward.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long> {
}
