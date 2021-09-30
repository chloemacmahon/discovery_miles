package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.activity.DrivingActivity;
import za.ac.nwu.ac.domain.dto.activity.HealthActivity;
import za.ac.nwu.ac.domain.dto.activity.SpendingActivity;
import za.ac.nwu.ac.domain.dto.goal.Goal;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.exception.InvalidGameTileException;
import za.ac.nwu.ac.domain.exception.InvalidGoalPointsException;
import za.ac.nwu.ac.repository.ActivityRepository;

import java.util.List;

import static za.ac.nwu.ac.domain.dto.helper_classes.ReceiveInputs.getIntInput;

@Component
public class AddMilesServiceImpl implements AddMilesService{

    private ActivityRepository activityRepository;

    @Autowired
    public AddMilesServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Allows user to choose a tile to reveal then adds the miles to the za.ac.nwu.ac.domain.dto.member
     * Calls the <code>revealTile</code> method of the <code>GameBoard</code> class
     *
     * @param row    the row number of the tile that the user wants to reveal
     * @param column the column number of the tile that the user wants to reveal
     */

    @Override
    public void chooseGameTile(int row, int column, Member member) {
        int miles = member.getGameBoard().revealTile(row, column);
        if (miles < 0) {
            throw new InvalidGameTileException();
        } else {
            member.addMiles(miles);
            System.out.println("You have earned " + miles + " miles by revealing this tile, your total miles is now " + member.getMiles());
        }
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the method returns true
     * @param points the amount of points that the user has earn by doing an activity relating to their health, spending or driving za.ac.nwu.ac.domain.dto.goal
     * @param goal the goal that points are being added to
     * @return returns true if goal has been accomplished
     */
    @Override
    public boolean addPointsToGoal(int points, Goal goal) {
        if(!goal.isGoalAccomplished()){
            goal.addPoints(points);
            return goal.isGoalAccomplished();
        }
        return false;
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's health za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the method returns true
     * @param healthActivity the health activity the user has done
     * @return true if goal is accomplished
     */

    @Override
    public boolean addPointsToHealthGoal(HealthActivity healthActivity, Member member) {
        return addPointsToGoal(healthActivity.getPointsEarned(), member.getHealthGoal());
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's driving za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the method returns true
     * @param drivingActivity the driving activity the user has done
     * @return true if goal is accomplished
     */
    @Override
    public boolean addPointsToDrivingGoal(DrivingActivity drivingActivity, Member member) {
        return addPointsToGoal(drivingActivity.getPointsEarned(), member.getDrivingGoal());
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's spending za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the method returns true
     * @param spendingActivity the spending activity the user has done
     * @return true if goal is accomplished
     */
    @Override
    public boolean addPointsToSpendingGoal(SpendingActivity spendingActivity, Member member) {
        return addPointsToGoal(spendingActivity.getPointsEarned(), member.getSpendingGoal());
    }

    @Override
    public List<Activity> viewActivities() {
        return activityRepository.findAll();
    }

}
