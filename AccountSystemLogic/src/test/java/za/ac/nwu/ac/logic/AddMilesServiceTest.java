package za.ac.nwu.ac.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.domain.dto.member.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (classes = {AddMilesService.class})
class AddMilesServiceTest {

    private Member member;
    private AddMilesService addMilesService;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.createWeeklyGoals(500);
        addMilesService = new AddMilesServiceImpl(member);
    }

    @Test
    void addPointsToHealthGoalTest() {
        addMilesService.addPointsToHealthGoal(300);
        Assertions.assertEquals(member.getHealthGoal().getPointsEarned(), 300);
    }

    @Test
    void addPointsToDrivingGoalTest() {
        addMilesService.addPointsToDrivingGoal(300);
        Assertions.assertEquals(member.getDrivingGoal().getPointsEarned(), 300);
    }

    @Test
    void addPointsToSpendingGoalTest() {
        addMilesService.addPointsToSpendingGoal(300);
        Assertions.assertEquals(member.getSpendingGoal().getPointsEarned(), 300);
    }
}