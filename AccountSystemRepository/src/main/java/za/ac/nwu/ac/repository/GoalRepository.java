package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.ac.domain.dto.goal.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
