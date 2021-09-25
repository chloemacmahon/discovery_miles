package za.ac.nwu.ac.domain.dto.goal;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Health za.ac.nwu.ac.domain.dto.goal represents the za.ac.nwu.ac.domain.dto.member's weekly health za.ac.nwu.ac.domain.dto.goal, this level of abstraction is used so that
 * if how a za.ac.nwu.ac.domain.dto.goal is accomplished is determined by the za.ac.nwu.ac.domain.dto.goal itself that the relevant changes can be made without structural changes being necessary
 */

@Data
@Entity
@Component
@DiscriminatorValue("Health")
public class HealthGoal extends Goal {

    public HealthGoal() {
    }

    public HealthGoal(int totalPointsNeeded) {
        super("Health", totalPointsNeeded);
    }

    public HealthGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate) {
        super("Health", totalPointsNeeded, pointsEarned, goalAccomplished, startDate);
    }
}
