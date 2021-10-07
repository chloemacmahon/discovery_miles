import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.logic.Validator;

@SpringBootTest(classes = {Validator.class})
public class ValidatorTest {
    @Test
    void verifyInvalidIDNumberTest() {
        String idNumber = "0041300123182";

        Assertions.assertFalse(Validator.isValidID(idNumber));
    }

    @Test
    void verifyValidIDNumberTest() {
        String idNumber = "0009080131080";

        Assertions.assertTrue(Validator.isValidID(idNumber));
    }

    @Test
    void verifyValidPasswordTest() {
        String password = "FL0r3nce6?";

        Assertions.assertTrue(Validator.isValidPassword(password));
    }

    @Test
    void verifyInvalidPasswordTest() {
        String password = "FL0ce?";

        Assertions.assertFalse(Validator.isValidPassword(password));
    }

    @Test
    void verifyValidEmailTest() {
        String email = "jane@est.co.za";

        Assertions.assertTrue(Validator.isValidEmail(email));
    }

    @Test
    void verifyInvalidEmailTest() {
        String email = "jane@za";

        Assertions.assertFalse(Validator.isValidEmail(email));
    }
    
    @Test
    void verifyValidNameTest() {
        String name = "jane";

        Assertions.assertTrue(Validator.isValidName(name));
    }

    @Test
    void verifyInvalidNameTest() {
        String name = "jane@za";

        Assertions.assertFalse(Validator.isValidEmail(name));
    }
}
