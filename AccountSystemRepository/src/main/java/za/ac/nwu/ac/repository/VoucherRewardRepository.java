package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;

@Repository
public interface VoucherRewardRepository extends JpaRepository<VoucherReward, Long> {
}
