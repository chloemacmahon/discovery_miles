package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;

@Repository
public interface RewardPartnerRepository extends JpaRepository<RewardPartner, Long> {
    RewardPartner findByEmail(String email);
}
