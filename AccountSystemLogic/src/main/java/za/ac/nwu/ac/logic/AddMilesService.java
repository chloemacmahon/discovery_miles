package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.goal.Goal;
import za.ac.nwu.ac.domain.dto.member.Member;

import static za.ac.nwu.ac.domain.dto.helper_classes.ReceiveInputs.getIntInput;

public interface AddMilesService {

    void chooseGameTIle(int row, int column);

    boolean addPointsToGoal(int points, Goal goal);

    boolean addPointsToHealthGoal(int points);

    boolean addPointsToDrivingGoal(int points);

    boolean addPointsToSpendingGoal(int points);

}
