package za.ac.nwu.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.nwu.ac.domain.dto.goal.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
