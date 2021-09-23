package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;

public interface VoucherRewardRepository extends JpaRepository<VoucherReward, Long> {
}
