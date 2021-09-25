package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.goal.Goal;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.exception.InvalidGameTileException;
import za.ac.nwu.ac.domain.exception.InvalidGoalPointsException;

import static za.ac.nwu.ac.domain.dto.helper_classes.ReceiveInputs.getIntInput;

public class AddMilesServiceImpl implements AddMilesService{

    private Member member;

    public AddMilesServiceImpl(Member member) {
        this.member = member;
    }

    /**
     * Allows user to choose a tile to reveal then adds the miles to the za.ac.nwu.ac.domain.dto.member
     * Calls the <code>revealTile</code> method of the <code>GameBoard</code> class
     *
     * @param row    the row number of the tile that the user wants to reveal
     * @param column the column number of the tile that the user wants to reveal
     */

    @Override
    public void chooseGameTIle(int row, int column) {
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
     * @param points the amount of points that the user has earn by doing an activity relating to their health goal
     * @return true if goal is accomplished
     */

    public boolean addPointsToHealthGoal(int points) {
        return addPointsToGoal(points, member.getHealthGoal());
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's driving za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the method returns true
     * @param points the amount of points that the user has earn by doing an activity relating to their driving goal
     * @return true if goal is accomplished
     */

    public boolean addPointsToDrivingGoal(int points) {
        return addPointsToGoal(points, member.getDrivingGoal());
    }

    /**
     * Adds points to the za.ac.nwu.ac.domain.dto.member's spending za.ac.nwu.ac.domain.dto.goal
     * If the za.ac.nwu.ac.domain.dto.goal is accomplished the method returns true
     * @param points the amount of points that the user has earn by doing an activity relating to their spending goal
     * @return true if goal is accomplished
     */

    public boolean addPointsToSpendingGoal(int points) {
        return addPointsToGoal(points, member.getSpendingGoal());
    }

}
