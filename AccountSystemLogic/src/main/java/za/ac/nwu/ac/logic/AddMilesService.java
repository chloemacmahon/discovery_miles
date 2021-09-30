package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.activity.DrivingActivity;
import za.ac.nwu.ac.domain.dto.activity.HealthActivity;
import za.ac.nwu.ac.domain.dto.activity.SpendingActivity;
import za.ac.nwu.ac.domain.dto.goal.DrivingGoal;
import za.ac.nwu.ac.domain.dto.goal.Goal;
import za.ac.nwu.ac.domain.dto.member.Member;

import java.util.List;


public interface AddMilesService {

    void chooseGameTile(int row, int column, Member member);

    boolean addPointsToGoal(int points, Goal goal);

    boolean addPointsToHealthGoal(HealthActivity healthActivity, Member member);

    boolean addPointsToDrivingGoal(DrivingActivity drivingActivity, Member member);

    boolean addPointsToSpendingGoal(SpendingActivity spendingActivity, Member member);

    List<Activity> viewActivities();

    //Throw error

}
