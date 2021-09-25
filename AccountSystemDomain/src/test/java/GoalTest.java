import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import za.ac.nwu.ac.domain.dto.goal.HealthGoal;
import za.ac.nwu.ac.domain.exception.InvalidGoalPointsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.domain.dto.goal.Goal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@SpringBootTest(classes = {za.ac.nwu.ac.domain.dto.goal.Goal.class})
@RunWith(MockitoJUnitRunner.class)
class GoalTest {

//    @Mock
//    private AccountTypeTranslator mock;
//
//    @InjectMocks
//    private CreateAccountTypeFlowImpl Goal;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    void addPointsIncompleteGoalTest() {
        Goal goal = new HealthGoal(600);
        goal.addPoints(500);
        Assertions.assertFalse(goal.isGoalAccomplished());
    }

    @Test
    void addPointsCompleteGoalTest() {
        Goal goal = new HealthGoal(60);
        goal.addPoints(600);
        Assertions.assertTrue(goal.isGoalAccomplished());
    }

    @Test
    void addPointsExceptionTest() {
        Goal goal = new HealthGoal(600);
        goal.addPoints(600);
        Exception exception = Assertions.assertThrows(InvalidGoalPointsException.class, () -> {
            goal.addPoints(100);
        });
        Assertions.assertTrue(exception.getMessage().contains("The goal has already been accomplished and points can not be added"));
    }

}
