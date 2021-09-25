import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.domain.exception.InsufficientMilesException;
import za.ac.nwu.ac.domain.dto.member.Member;

@SpringBootTest(classes = {za.ac.nwu.ac.domain.dto.member.Member.class})
class MemberTest {

    @Test
    void addMilesTest() {
        Member member = new Member();
        member.addMiles(300);
        Assertions.assertEquals(300, member.getMiles());
    }

    @Test
    void createHealthGoalTest() {
        Member member = new Member();
        member.createWeeklyGoals(600);
        Assertions.assertNotNull(member.getHealthGoal());
    }

    @Test
    void createDrivingGoalTest() {
        Member member = new Member();
        member.createWeeklyGoals(600);
        Assertions.assertNotNull(member.getDrivingGoal());
    }

    @Test
    void createSpendingGoalTest() {
        Member member = new Member();
        member.createWeeklyGoals(600);
        Assertions.assertNotNull(member.getSpendingGoal());
    }

    @Test
    void sufficientMilesToSubtractTest() {
        Member member = new Member();
        member.addMiles(400);
        member.subtractMiles(300);
        Assertions.assertEquals(100, member.getMiles());
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

    /*@Test
    void revealTileTest() {
        Member member = new Member("","","","","");

    }*/

}
