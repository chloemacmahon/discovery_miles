package za.ac.nwu.ac.domain.dto.goal;

import java.time.LocalDate;

/**
 * Spending za.ac.nwu.ac.domain.dto.goal represents the za.ac.nwu.ac.domain.dto.member's weekly spending za.ac.nwu.ac.domain.dto.goal, this level of abstraction is used so that
 * if how a za.ac.nwu.ac.domain.dto.goal is accomplished is determined by the za.ac.nwu.ac.domain.dto.goal itself that the relevant changes can be made without structural changes being necessary
 */

public class SpendingGoal extends Goal {

    public SpendingGoal(int totalPointsNeeded, Long memberID) {
        super("Spending", totalPointsNeeded, memberID);
    }

    public SpendingGoal(int totalPointsNeeded, int pointsEarned, boolean goalAccomplished, LocalDate startDate, Long memberID) {
        super("Spending", totalPointsNeeded, pointsEarned, goalAccomplished, startDate,memberID);
    }
}
