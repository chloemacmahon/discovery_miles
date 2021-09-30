package za.ac.nwu.ac.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.domain.dto.activity.DrivingActivity;
import za.ac.nwu.ac.domain.dto.activity.HealthActivity;
import za.ac.nwu.ac.domain.dto.activity.SpendingActivity;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.repository.ActivityRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (classes = {AddMilesService.class})
class AddMilesServiceTest {

    private AddMilesService addMilesService;
    private ActivityRepository activityRepository;

    @BeforeEach
    void setUp(){
        //addMilesService = new AddMilesServiceImpl(ActivityRepository);
    }
/*
    @Test
    void addPointsToHealthGoalTest() {
        Member member = new Member("name", "surname", "0","","00");
        //member.createWeeklyGoals(600);
        HealthActivity healthActivity = new HealthActivity("Check tire pressure", 300);
        addMilesService.addPointsToHealthGoal(healthActivity, member);
        Assertions.assertEquals(member.getHealthGoal().getPointsEarned(), 300);
    }

    @Test
    void addPointsToDrivingGoalTest() {
        Member member = new Member();
        member.createWeeklyGoals(600);
        DrivingActivity drivingActivity = new DrivingActivity("Check tire pressure", 300);
        addMilesService.addPointsToDrivingGoal(drivingActivity, member);
        Assertions.assertEquals(member.getDrivingGoal().getPointsEarned(), 300);
    }

    @Test
    void addPointsToSpendingGoalTest() {
        Member member = new Member();
        member.createWeeklyGoals(600);
        SpendingActivity spendingActivity = new SpendingActivity("Check tire pressure", 300);
        addMilesService.addPointsToSpendingGoal(spendingActivity, member);
        Assertions.assertEquals(member.getSpendingGoal().getPointsEarned(), 300);
    }*/
}