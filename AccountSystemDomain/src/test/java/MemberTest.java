import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.domain.dto.goal.Goal;
import za.ac.nwu.ac.domain.dto.helper_classes.exception.InsufficientMilesException;
import za.ac.nwu.ac.domain.dto.helper_classes.exception.InvalidGoalPointsException;
import za.ac.nwu.ac.domain.dto.member.Member;

@SpringBootTest(classes = {za.ac.nwu.ac.domain.dto.member.Member.class})
class MemberTest {

    @Test
    void verifyInvalidIDNumberTest() {
        String idNumber = "0041300123182";

        Assertions.assertFalse(Member.isValidID(idNumber));
    }

    @Test
    void verifyValidIDNumberTest() {
        String idNumber = "0009080131080";

        Assertions.assertTrue(Member.isValidID(idNumber));
    }

    @Test
    void addMilesTest() {
        Member member = new Member();
        member.addMiles(300);
        Assertions.assertEquals(300,member.getMiles());
    }

    @Test
    void createGoalsTest() {
        Member member =  new Member();
        member.createWeeklyGoals(600);
        Assertions.assertEquals(3,member.getGoals().size());
    }

    @Test
    void sufficientMilesToSubtractTest() {
        Member member = new Member();
        member.addMiles(400);
        member.subtractMiles(300);
        Assertions.assertEquals(100,member.getMiles());
    }

    @Test
    void insufficientMilesToSubtractTest() {
        Member member = new Member();
        member.addMiles(400);
        Exception exception = Assertions.assertThrows(InsufficientMilesException.class, () -> {
            member.subtractMiles(410);
        });
        Assertions.assertTrue(exception.getMessage().contains("Not enough miles available"));
    }

}
