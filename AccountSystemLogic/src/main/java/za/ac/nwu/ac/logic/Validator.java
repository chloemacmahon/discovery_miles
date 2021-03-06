package za.ac.nwu.ac.logic;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Used to validate all user inputs
 */
@Data
@Component
public class Validator {

    /**
     * Validates that a name / surname only contains letters
     * @param name The name that needs to be verified
     * @return True if the name only contains letters
     */

    public static Boolean isValidName(String name){
        for (Character letter: name.toCharArray()) {
            if (!Character.isLetter(letter))
                return false;
        }
        return true;
    }

    /**
     * Validates the za.ac.nwu.ac.domain.dto.member's ID number
     *
     * @param idNumber the za.ac.nwu.ac.domain.dto.member's ID number
     * @return true if the za.ac.nwu.ac.domain.dto.member's ID number is valid
     */

    public static Boolean isValidID(String idNumber) {
        if (idNumber.length() != 13)
            return false;
        for (char digit : idNumber.toCharArray()) {
            if (!Character.isDigit(digit))
                return false;
        }
        char[] idNumberArray = idNumber.toCharArray();
        if (((Character.getNumericValue(idNumberArray[2]) * 10) + Character.getNumericValue(idNumberArray[3])) > 12)
            return false;

        return ((Character.getNumericValue(idNumberArray[4]) * 10) + Character.getNumericValue(idNumberArray[5])) <= 31;
    }

    /**
     * Validates that a user's password is:
     * At least 8 characters long
     * Has at least 2 digits
     * Has at least 2 capital letters
     * Has at least 1 special character
     *
     * @param password A user's possible password
     * @return True if the password is valid/ strong enough
     */
    public static boolean isValidPassword(String password) {
        if (password.length() < 8)
            return false;
        int numOfDigits = 0;
        int numOfCapitalLetters = 0;
        int numOfSpecialCharacters = 0;
        for (char letter : password.toCharArray()) {
            if (Character.isDigit(letter)) {
                numOfDigits++;
            }
            if (Character.isUpperCase(letter)) {
                numOfCapitalLetters++;
            }
            if (!Character.isLetterOrDigit(letter) && letter != ' ') {
                numOfSpecialCharacters++;
            }
        }
        return numOfCapitalLetters >= 2 && numOfDigits >= 2 && numOfSpecialCharacters >= 1;
    }

    /**
     * Validates that a user's email is valid by checking:
     * That there is a @ sign
     * That it is followed by a dot
     *
     * @param email A user's email
     * @return True if the email is valid
     */
    public static boolean isValidEmail(String email) {
        String[] emailParts = email.split("@");
        if (emailParts.length == 2) {
            String[] domainParts = emailParts[1].split("[.]");
            return domainParts.length >= 2;
        } else
            return false;
    }
}
