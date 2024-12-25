package validations;

import java.util.regex.Pattern;

public class PasswordValidator {

    // Regular expression for validating a password
    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    /**
     * Validates the password according to the following rules:
     * - At least 8 characters long
     * - Contains at least one uppercase letter
     * - Contains at least one lowercase letter
     * - Contains at least one digit
     * - Contains at least one special character
     *
     * @param password The password to validate
     * @return true if the password is valid, false otherwise
     */
    public static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }

        // Match the password against the regular expression
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        return pattern.matcher(password).matches();
    }


}
