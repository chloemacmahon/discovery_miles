package za.ac.nwu.ac.domain.dto.goal;

import java.time.LocalDate;

/**
 * Health za.ac.nwu.ac.domain.dto.goal represents the za.ac.nwu.ac.domain.dto.member's weekly health za.ac.nwu.ac.domain.dto.goal, this level of abstraction is used so that
 * if how a za.ac.nwu.ac.domain.dto.goal is accomplished is determined by the za.ac.nwu.ac.domain.dto.goal itself that the relevant changes can be made without structural changes being necessary
 */


public class HealthGoal extends Goal {

    public HealthGoal(int totalPointsNeeded, Long memberID) {
        super("Health", totalPointsNeeded,memberID);
    }

    public HealthGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, Long memberID) {
        super("Health", totalPointsNeeded, pointsEarned, goalAccomplished, startDate, memberID);
    }
}
