import za.ac.nwu.ac.domain.exception.InvalidGoalPointsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.domain.dto.goal.Goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {za.ac.nwu.ac.domain.dto.goal.Goal.class})
class GoalTest {

    @Test
    void addPointsIncompleteGoalTest() {
        Goal goal = new Goal("Health",600,0L);
        goal.addPoints(500);
        Assertions.assertFalse(goal.isGoalAccomplished());
    }

    @Test
    void addPointsCompleteGoalTest() {
        Goal goal = new Goal("Health",600, 0L);
        goal.addPoints(600);
        Assertions.assertTrue(goal.isGoalAccomplished());
    }

    @Test
    void addPointsExceptionTest() {
        Goal goal = new Goal("Health",600, 0L);
        goal.addPoints(600);
        Exception exception = Assertions.assertThrows(InvalidGoalPointsException.class, () -> {
            goal.addPoints(100);
        });
        Assertions.assertTrue(exception.getMessage().contains("The goal has already been accomplished and points can not be added"));
    }

}
