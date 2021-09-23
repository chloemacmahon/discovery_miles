import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.logic.FieldValidation;

@SpringBootTest(classes = {za.ac.nwu.ac.logic.FieldValidation.class})
public class FieldValidationTest {
    @Test
    void verifyInvalidIDNumberTest() {
        String idNumber = "0041300123182";

        Assertions.assertFalse(FieldValidation.isValidID(idNumber));
    }

    @Test
    void verifyValidIDNumberTest() {
        String idNumber = "0009080131080";

        Assertions.assertTrue(FieldValidation.isValidID(idNumber));
    }

    @Test
    void verifyValidPasswordTest() {
        String password = "FL0r3nce6?";

        Assertions.assertTrue(FieldValidation.isValidPassword(password));
    }

    @Test
    void verifyInvalidPasswordTest() {
        String password = "FL0ce?";

        Assertions.assertFalse(FieldValidation.isValidPassword(password));
    }

    @Test
    void verifyValidEmailTest() {
        String email = "jane@est.co.za";

        Assertions.assertTrue(FieldValidation.isValidEmail(email));
    }

    @Test
    void verifyInvalidEmailTest() {
        String email = "jane@za";

        Assertions.assertFalse(FieldValidation.isValidEmail(email));
    }
}
