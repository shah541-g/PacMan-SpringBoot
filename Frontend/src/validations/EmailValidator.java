package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    public static boolean validateEmail(String email) {
        // Create a Pattern object
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        // Create matcher object
        Matcher matcher = pattern.matcher(email);

        // Return whether the email matches the regex pattern
        return matcher.matches();
    }
}
