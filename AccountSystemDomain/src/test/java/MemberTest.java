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
